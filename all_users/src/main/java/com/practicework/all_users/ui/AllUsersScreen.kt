package com.practicework.all_users.ui

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

