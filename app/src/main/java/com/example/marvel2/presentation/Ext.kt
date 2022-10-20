package com.example.marvel2.presentation

import android.content.Context
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.marvel2.navigation.CommonNavGraphNavigator
import com.example.marvel2.navigation.NavGraphs
import com.ramcosta.composedestinations.scope.DestinationScope
import com.ramcosta.composedestinations.spec.NavGraphSpec

fun NavDestination.navGraph(): NavGraphSpec {
    hierarchy.forEach { destination ->
        NavGraphs.root.nestedNavGraphs.forEach { navGraph ->
            if (destination.route == navGraph.route) {
                return navGraph
            }
        }
    }
    throw RuntimeException("Unknown nav graph for destination $route")
}

fun DestinationScope<*>.currentNavigator(context: Context): CommonNavGraphNavigator {
    return CommonNavGraphNavigator(
        context,
        navBackStackEntry.destination.navGraph(),
        navController
    )
}