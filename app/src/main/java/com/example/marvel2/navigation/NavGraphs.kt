package com.example.marvel2.navigation

import com.home.presentation.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.dynamic.routedIn
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec

object NavGraphs {
    val home = object : NavGraphSpec {
        override val route = "home"
        override val startRoute = HomeScreenDestination routedIn this
        override val destinationsByRoute = listOf<DestinationSpec<*>>(
            HomeScreenDestination,
        ).routedIn(this).associateBy { it.route }
    }

    val root = object : NavGraphSpec {
        override val route = "root"
        override val startRoute = home
        override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()
        override val nestedNavGraphs = listOf(home)
    }
}