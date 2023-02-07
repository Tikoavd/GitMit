package com.practicework.login.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practicework.login.presentation.LoginEvent
import com.practicework.login.presentation.LoginViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    vm: LoginViewModel = viewModel()
) {
    val loginState by vm.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .fillMaxHeight(0.18f),
            contentAlignment = Alignment.Center
        ) {
            LogoAndName()
        }

    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(0.6f),
            contentAlignment = Alignment.Center
        ) {
            SignInCard(
                send = { vm.send(it) },
                isLoading = loginState.isLoading,
                input = vm.userInput
            )

            if (loginState.isLoading) {
                CircularProgressIndicator()
            }
        }
    }

    val snackbarHostState = remember { SnackbarHostState() }

    Box(contentAlignment = Alignment.BottomCenter) {
        SnackbarHost(
            hostState = snackbarHostState
        ) {
            Snackbar(modifier = Modifier.fillMaxWidth(0.8f)) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = it.message
                )
            }
        }
    }


    if (loginState.isError) {
        LaunchedEffect(Unit) {

            val snackbarResult =
                snackbarHostState.showSnackbar(
                    message = loginState.errorMessage,
                    duration = SnackbarDuration.Short
                )
            if (snackbarResult == SnackbarResult.Dismissed) {
                vm.send(LoginEvent.CloseError)
            }
        }
    }
}