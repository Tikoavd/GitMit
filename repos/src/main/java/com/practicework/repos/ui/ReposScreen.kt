package com.practicework.repos.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.practicework.repos.domain.models.Repo
import com.practicework.repos.presentation.ReposEvent
import com.practicework.repos.presentation.ReposViewModel


@Composable
fun ReposScreen(
    modifier: Modifier = Modifier,
    vm: ReposViewModel = hiltViewModel()
) {
    val reposState by vm.uiState.collectAsState()

    val refreshState =
        rememberSwipeRefreshState(isRefreshing = reposState.isUpdating)

    SwipeRefresh(
        state = refreshState,
        onRefresh = { vm.send(ReposEvent.UpdateRepos) }
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 6.dp),
        ) {
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 40.sp,
                    text = "Your repositories"
                )
            }
            items(reposState.repos.size) { index ->
                if (index >= reposState.repos.size - 1 && !reposState.isLoading) {
                    vm.send(ReposEvent.GetMoreRepos)
                }
                RepoCard(repo = reposState.repos[index])
            }
            item {
                if (reposState.isLoading) {
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