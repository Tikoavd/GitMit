package com.practicework.profile.ui

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicework.profile.R
import com.practicework.profile.domain.models.User

@Composable
internal fun UserCard(
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
