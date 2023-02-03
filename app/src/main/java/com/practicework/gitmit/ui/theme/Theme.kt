package com.practicework.gitmit.ui.theme

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.practicework.gitmit.presentation.ThemeViewModel

private val DarkColorPalette = darkColors(
    primary = DarkGreen,
    primaryVariant = LightGreen,
    secondary = DarkYellow
)

private val LightColorPalette = lightColors(
    primary = LightGreen,
    primaryVariant = DarkGreen,
    secondary = LightYellow

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun GitMitTheme(
    vm: ThemeViewModel = hiltViewModel(),
    content: @Composable () -> Unit
) {
    val isDarkTheme by vm.nightMode.collectAsState()
    Log.e("theme", "comp: $isDarkTheme")
    val colors = when (isDarkTheme) {
        true -> { DarkColorPalette }
        false -> { LightColorPalette }
        else -> { if (isSystemInDarkTheme()) { DarkColorPalette} else LightColorPalette }
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}