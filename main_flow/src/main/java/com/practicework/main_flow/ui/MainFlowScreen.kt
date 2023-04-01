package com.practicework.main_flow.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainFlowScreen(
    navigateOnSignOut: () -> Unit,
    navHostController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navHostController)
        },
        backgroundColor = MaterialTheme.colors.background
    ) {
        Surface(Modifier.fillMaxHeight(0.92f)) {
            MainFlowNavigationGraph(
                navController = navHostController,
                navigateOnSignOut = navigateOnSignOut
            )
        }
    }
}


