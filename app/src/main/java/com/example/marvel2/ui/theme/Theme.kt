package com.example.marvel2.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(background = backgroundDark)

private val LightColorPalette = lightColors(background = backgroundLight)

@Composable
fun Marvel2Theme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    val view = LocalView.current
    val currentWindow = (view.context as? Activity)?.window
        ?: throw Exception("Not in an activity - unable to get Window reference")
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colors.primary.toArgb()
            currentWindow.statusBarColor = colors.primary.toArgb()

            WindowCompat.getInsetsController(currentWindow, view)
                .isAppearanceLightStatusBars =
                darkTheme

            systemUiController.setSystemBarsColor(
                color = Color.Transparent,
                darkIcons = !darkTheme,
            )
        }
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}