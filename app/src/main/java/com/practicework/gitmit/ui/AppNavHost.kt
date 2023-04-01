package com.practicework.gitmit.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practicework.login.ui.LoginScreen
import com.practicework.main_flow.ui.MainFlowScreen

const val MAIN_FLOW = "main_flow"
const val LOGIN = "login"

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = LOGIN
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = startDestination) {
            LoginScreen(navigateWhenSignedIn = {
                navController.navigate(MAIN_FLOW) {
                    popUpTo(0)
                }
            })
        }
        composable(route = MAIN_FLOW) {
            MainFlowScreen(
                navigateOnSignOut = {
                    navController.navigate(LOGIN) {
                        popUpTo(0)
                    }
                }
            )
        }
    }
}