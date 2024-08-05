package com.siddharthchordia.myrepoapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.siddharthchordia.myrepoapp.navigation.MyRepoAppHost
import com.siddharthchordia.myrepoapp.navigation.Route

@Composable
fun MyRepoApp(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    MyRepoAppHost(
        modifier = modifier,
        startDestination = Route.Home,
        navController = navController,
    )
}
