package com.siddharthchordia.myrepoapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.siddharthchordia.myrepoapp.navigation.MyRepoAppHost
import com.siddharthchordia.myrepoapp.navigation.Route

@Composable
fun MyRepoApp(
    modifier: Modifier = Modifier,
) {
    MyRepoAppHost(
        modifier = modifier,
        startDestination = Route.Home,
    )
}
