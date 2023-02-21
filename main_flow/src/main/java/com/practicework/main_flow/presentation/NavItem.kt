package com.practicework.main_flow.presentation

import androidx.navigation.NavHostController
import com.practicework.main_flow.R

sealed class NavItem(val title: String, val icon: Int, val route: String) {
    object Repos : NavItem("Repositories", R.drawable.repos_dark, "repos")
    object Users : NavItem("Community", R.drawable.users_dark, "users")
    object Profile : NavItem("Profile", R.drawable.profile_dark, "profile")
}
