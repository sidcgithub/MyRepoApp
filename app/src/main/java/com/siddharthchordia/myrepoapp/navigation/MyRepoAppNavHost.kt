package com.siddharthchordia.myrepoapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.siddharthchordia.myrepoapp.core.navigationshell.MyRepoAppScaffold
import com.siddharthchordia.myrepoapp.core.navigationshell.ScreenNavParams

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
        scaffoldedComposable<Route.Home>(ScreenNavParams(navController)) { innerPadding ->
            Text(
                text = "Hello, World!",
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}

inline fun <reified T : Any> NavGraphBuilder.scaffoldedComposable(
    screenNavParams: ScreenNavParams,
    crossinline content: @Composable (innerPadding: PaddingValues) -> Unit,
) {
    composable<T> {
        MyRepoAppScaffold(screenNavParams) { innerPadding ->
            content(innerPadding)
        }
    }
}
