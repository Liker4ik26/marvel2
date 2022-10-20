package com.example.marvel2.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.marvel2.navigation.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.rememberNavHostEngine

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    DestinationsNavHost(
        engine = rememberNavHostEngine(),
        navGraph = NavGraphs.root,
        navController = navController,
        modifier = modifier,
        dependenciesContainerBuilder = {
            dependency(currentNavigator(LocalContext.current))
        }
    )
}
