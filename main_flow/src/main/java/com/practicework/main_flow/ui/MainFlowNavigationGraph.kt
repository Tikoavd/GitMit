package com.practicework.main_flow.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.practicework.main_flow.presentation.NavItem
import com.practicework.repos.ui.ReposScreen

@Composable
internal fun MainFlowNavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavItem.Repos.route) {
        composable(NavItem.Repos.route) {
            ReposScreen()
        }

        composable(NavItem.Users.route) {
            Text(text = "users")
        }

        composable(NavItem.Profile.route) {
            Text(text = "profile")
        }
    }
}