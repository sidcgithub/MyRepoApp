package com.siddharthchordia.myrepoapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.siddharthchordia.myrepoapp.feature.navigationshell.MyRepoAppScaffold
import com.siddharthchordia.myrepoapp.feature.navigationshell.ScreenNavParams
import com.siddharthchordia.myrepoapp.feature.usersearch.UserSearch
import com.siddharthchordia.myrepoapp.feature.usersearch.UserSearchViewModel

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
            val searchViewModel: UserSearchViewModel = hiltViewModel()
            UserSearch(searchViewModel, innerPadding)
        }
    }
}

inline fun <reified T : Route> NavGraphBuilder.scaffoldedComposable(
    screenNavParams: ScreenNavParams,
    crossinline content: @Composable (innerPadding: PaddingValues) -> Unit,
) {
    composable<T> {
        MyRepoAppScaffold(screenNavParams) { innerPadding ->
            content(innerPadding)
        }
    }
}
