package com.example.marvel2.navigation

import android.content.Context
import androidx.navigation.NavController
import com.ramcosta.composedestinations.spec.NavGraphSpec


class CommonNavGraphNavigator(
    private val context: Context,
    private val navGraph: NavGraphSpec,
    private val navController: NavController
)
