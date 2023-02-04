package com.practicework.details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.practicework.details.R
import com.practicework.details.domain.models.Repo
import com.practicework.details.domain.models.User
import com.practicework.details.presentation.DetailsEvent
import com.practicework.details.presentation.DetailsViewModel

@Composable
fun DetailsScreen(
    vm: DetailsViewModel = hiltViewModel()
) {
    val detailsState by vm.uiState.collectAsState()

    if (detailsState.isError) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.fillMaxWidth(0.5f),
                painter = painterResource(id = R.drawable.no_wifi),
                contentDescription = "no wifi"
            )
        }

    } else {
        detailsState.user?.let { user ->
            DetailsLoadedScreen(
                user = user,
                repos = detailsState.repos,
                isUpdating = detailsState.isUpdating,
                isLoading = detailsState.isLoading,
                send = vm::send
            )
        }
    }
}

@Composable
private fun DetailsLoadedScreen(
    user: User,
    repos: List<Repo>,
    isUpdating: Boolean,
    isLoading: Boolean,
    send: (DetailsEvent) -> Unit
) {
    val refreshState =
        rememberSwipeRefreshState(isRefreshing = isUpdating)

    SwipeRefresh(
        state = refreshState,
        onRefresh = { send(DetailsEvent.Update) }
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 6.dp),
        ) {
            item {
                UserCard(user = user)
            }
            items(repos.size) { index ->
                if (index >= repos.size - 1 && !isLoading) {
                    send(DetailsEvent.GetMoreRepos)
                }
                RepoCard(repo = repos[index])
            }
            item {
                if (isLoading) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
private fun RepoCard(repo: Repo) {
    Card(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 6.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxSize(),
        ) {

            Text(
                modifier = Modifier.padding(4.dp),
                text = repo.name
            )

            Card(
                modifier = Modifier.wrapContentWidth(),
                shape = RoundedCornerShape(10.dp),
                elevation = 4.dp
            ) {
                Text(
                    modifier = Modifier.padding(
                        horizontal = 6.dp,
                        vertical = 4.dp
                    ),
                    text = repo.visibility
                )
            }


            Text(
                modifier = Modifier.padding(4.dp),
                text = "Language: ${repo.language}"
            )
        }
    }
}

@Composable
private fun UserCard(user: User) {
    Box(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 6.dp)
            .fillMaxWidth()
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            AsyncImage(
                model = user.avatarUrl,
                contentDescription = "avatar",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

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