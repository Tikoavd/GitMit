package com.practicework.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practicework.login.R
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

@Composable
private fun LogoAndName() {
    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .fillMaxHeight(),
            painter = if (MaterialTheme.colors.isLight)
                painterResource(R.drawable.logo_icon_light)
            else painterResource(R.drawable.logo_icon_dark),
            contentDescription = "logo",
            contentScale = ContentScale.FillHeight
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                fontSize = 50.sp,
                text = "GitMit",
            )
        }

    }
}


@Composable
private fun SignInCard(
    send: (LoginEvent) -> Unit,
    isLoading: Boolean,
    input: String
) {
    Card(
        modifier = Modifier.wrapContentSize(),
        shape = RoundedCornerShape(8.dp),
        elevation = 12.dp
    ) {
        Column() {
            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 25.sp,
                text = "Sign In",
                textAlign = TextAlign.Center

            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                fontSize = 18.sp,
                text = "Please enter your personal access token",
                textAlign = TextAlign.Center
            )

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = input,
                onValueChange = { send(LoginEvent.InputChange(it)) },
                label = { Text("Enter token") },
                enabled = !isLoading
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                OutlinedButton(
                    enabled = !isLoading,
                    colors = ButtonDefaults
                        .buttonColors(backgroundColor = MaterialTheme.colors.primary),
                    onClick = {
                        send(LoginEvent.SignIn)
                    }
                ) {
                    Text(
                        text = "Sign in",
                        color = Color.White
                    )
                }

                OutlinedButton(
                    enabled = !isLoading,
                    colors = ButtonDefaults
                        .buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                    onClick = {
                        send(LoginEvent.GetToken)
                    }
                ) {
                    Text(
                        text = "Get token",
                        color = Color.White
                    )
                }
            }
        }
    }
}
