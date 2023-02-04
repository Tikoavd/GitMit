package com.practicework.all_users.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.practicework.details.presentation.DetailsViewModel.Companion.USER_LOGIN
import com.practicework.details.ui.DetailsScreen

const val ALL_USERS_ROUTE = "allUsers"
const val DETAILS_ROUTE = "details/{$USER_LOGIN}"
const val DETAILS_ROOT = "details/"

@Composable
fun AllUsersNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = ALL_USERS_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(ALL_USERS_ROUTE) { backStackEntry ->
            AllUsersScreen(onNavigateToDetails = { login ->
                navController.navigate("$DETAILS_ROOT$login") {
                    launchSingleTop = true
                }
            })
        }

        composable(
            DETAILS_ROUTE,
            arguments = listOf(navArgument(USER_LOGIN) { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString(USER_LOGIN)?.let {
                DetailsScreen()
            }
        }
    }
}