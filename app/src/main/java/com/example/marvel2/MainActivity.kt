package com.example.marvel2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.marvel2.presentation.AppHost
import com.example.marvel2.ui.theme.Marvel2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Marvel2Theme {
        AppHost()
            }
        }
    }
}
