package com.practicework.profile.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.practicework.profile.R
import com.practicework.profile.domain.models.User
import com.practicework.profile.presentation.ProfileEvent
import com.practicework.profile.presentation.ProfileViewModel

@Composable
fun ProfileScreen(
    navigateOnSignOut: () -> Unit,
    modifier: Modifier = Modifier,
    vm: ProfileViewModel = hiltViewModel()
) {
    val profileState by vm.uiState.collectAsState()

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        uri?.let {
            vm.send(ProfileEvent.SaveUri(it))
        }
    }

    val reqPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            if (it) {
                launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
        }
    )

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 6.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 40.sp,
                text = "Profile"
            )
        }

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Night mode:",
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Switch(
                    checked = profileState.isNightMode ?: isSystemInDarkTheme(),
                    onCheckedChange =  { isNighMode ->
                        vm.send(ProfileEvent.SaveNightMode(isNighMode))
                    })
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.6f)
            ) {
                UserCard(
                    user = profileState.user,
                    bitmap = profileState.bitmap,
                    onImageClick = {
                        if(!checkPermission(context)) {

                                if (Build.VERSION.SDK_INT <= 32) {
                                    reqPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                                } else {
                                    reqPermission.launch(Manifest.permission.READ_MEDIA_IMAGES)
                                }

                        } else {
                            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        }
                    }
                )
            }

            OutlinedButton(
                modifier = Modifier.wrapContentWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                onClick = {
                    vm.send(ProfileEvent.SignOut)
                    navigateOnSignOut()
                }
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    text = "Sign out"
                )
            }
        }

    }
}

@Composable
private fun UserCard(
    user: User,
    bitmap: Bitmap?,
    onImageClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 6.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (bitmap != null) {
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "avatar",
                    modifier = Modifier
                        .fillMaxHeight(0.6f)
                        .padding(12.dp)
                        .clip(CircleShape)
                        .clickable {
                            onImageClick()
                        },
                    contentScale = ContentScale.FillHeight
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = "avatar",
                    modifier = Modifier
                        .fillMaxHeight(0.6f)
                        .padding(12.dp)
                        .clip(CircleShape)
                        .clickable {
                            onImageClick()
                        },
                    contentScale = ContentScale.FillHeight
                )
            }


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    text = user.login
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                FollowCard(
                    text = "followers",
                    count = user.followersCount,
                    color = MaterialTheme.colors.primary
                )

                Spacer(modifier = Modifier.width(8.dp))

                FollowCard(
                    text = "following",
                    count = user.followingCount,
                    color = MaterialTheme.colors.secondary
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
private fun FollowCard(
    text: String,
    count: Int,
    color: Color
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 16.dp,
        backgroundColor = color
    ) {
        Text(
            modifier = Modifier.padding(2.dp),
            text = "$text $count",
            color = Color.White
        )
    }
}

private fun checkPermission(context: Context): Boolean {
    return if (Build.VERSION.SDK_INT <= 32) {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    } else {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_MEDIA_IMAGES
        ) == PackageManager.PERMISSION_GRANTED
    }
}