package com.practicework.main_flow.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.practicework.all_users.ui.AllUsersNavHost
import com.practicework.main_flow.presentation.NavItem
import com.practicework.profile.ui.ProfileScreen
import com.practicework.repos.ui.ReposScreen

@Composable
internal fun MainFlowNavigationGraph(
    navController: NavHostController,
    navigateOnSignOut: () -> Unit
) {
    NavHost(navController = navController, startDestination = NavItem.Repos.route) {
        composable(NavItem.Repos.route) {
            ReposScreen()
        }

        composable(NavItem.Users.route) {
            AllUsersNavHost()
        }

        composable(NavItem.Profile.route) {
            ProfileScreen(navigateOnSignOut = { navigateOnSignOut() })
        }
    }
}