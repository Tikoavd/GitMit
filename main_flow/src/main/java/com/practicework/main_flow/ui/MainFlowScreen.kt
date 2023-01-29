package com.practicework.main_flow.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.practicework.main_flow.presentation.NavItem
import com.practicework.repos.ui.ReposScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainFlowScreen(
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = "repos"
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navHostController)
        }
    ) {
        Surface(Modifier.fillMaxHeight(0.92f)) {
            NavigationGraph(navController = navHostController)
        }
    }
}

@Composable
private fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        NavItem.Repos,
        NavItem.Users,
        NavItem.Profile
    )

    BottomNavigation(
        backgroundColor = Color.Black,
        modifier = Modifier.fillMaxHeight(0.08f)
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                icon = { Icon(painterResource(item.icon), item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                onClick = {
                    navController.navigate(item.route) {
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}

@Composable
private fun NavigationGraph(navController: NavHostController) {
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