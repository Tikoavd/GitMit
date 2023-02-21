package com.practicework.all_users.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.practicework.all_users.R
import com.practicework.all_users.domain.models.AllUser
import com.practicework.all_users.presentation.AllUsersEvent
import com.practicework.all_users.presentation.AllUsersViewModel

@Composable
fun AllUsersScreen(
    modifier: Modifier = Modifier,
    vm: AllUsersViewModel = hiltViewModel()
) {
    val allUsersState by vm.uiState.collectAsState()
    val refreshState =
        rememberSwipeRefreshState(isRefreshing = allUsersState.isUpdating)

    SwipeRefresh(
        state = refreshState,
        onRefresh = { vm.send(AllUsersEvent.Update) }
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 6.dp)
        ) {
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 40.sp,
                    text = "All users"
                )
            }
            items(allUsersState.userList.size) { index ->
                if (index >= allUsersState.userList.size - 1 && !allUsersState.isLoading) {
                    vm.send(AllUsersEvent.GetMore)
                }
                UserCard(allUsersState.userList[index])
            }
            item {
                if (allUsersState.isLoading) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun UserCard(user: AllUser) {
    Card(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 6.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (user.avatarUrl == AllUser.AVATAR_UNKNOWN) {
                Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = "avatar",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            else {
                AsyncImage(
                    model = user.avatarUrl,
                    contentDescription = "avatar",
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(Modifier.width(8.dp))
            Text(text = user.login, fontSize = 25.sp)
        }
    }
}