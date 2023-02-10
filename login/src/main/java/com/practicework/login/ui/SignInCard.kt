package com.practicework.login.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicework.login.presentation.LoginEvent

@Composable
internal fun SignInCard(
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