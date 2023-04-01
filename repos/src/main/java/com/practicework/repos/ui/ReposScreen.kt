package com.practicework.repos.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
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