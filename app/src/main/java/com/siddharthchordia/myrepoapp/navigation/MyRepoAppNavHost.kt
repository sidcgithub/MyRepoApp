package com.siddharthchordia.myrepoapp.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MyRepoAppHost(
    modifier: Modifier = Modifier,
    startDestination: Route = Route.Home,
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable<Route.Home> {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Text(
                    text = "Hello, World!",
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }
    }
}
