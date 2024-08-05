package com.siddharthchordia.myrepoapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.siddharthchordia.myrepoapp.feature.navigationshell.MyRepoAppScaffold
import com.siddharthchordia.myrepoapp.feature.navigationshell.ScreenNavParams
import com.siddharthchordia.myrepoapp.feature.repodetails.RepoDetailScreen
import com.siddharthchordia.myrepoapp.feature.repodetails.RepoDetailsViewModel
import com.siddharthchordia.myrepoapp.feature.usersearch.HomeScreen
import com.siddharthchordia.myrepoapp.feature.usersearch.UserSearchViewModel

@Composable
fun MyRepoAppHost(
    modifier: Modifier = Modifier,
    startDestination: Route = Route.Home,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        scaffoldedComposable<Route.Home>(ScreenNavParams.NoBack(navController)) { innerPadding ->
            val searchViewModel: UserSearchViewModel = hiltViewModel()
            HomeScreen(searchViewModel, { navController.navigate(Route.RepoDetails) }, innerPadding)
        }

        scaffoldedComposable<Route.RepoDetails>(ScreenNavParams.BackEnabled(navController) { navController.navigateUp() }) { innerPadding ->
            val repoDetailsViewModel: RepoDetailsViewModel = hiltViewModel()
            RepoDetailScreen(repoDetailsViewModel, innerPadding)
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
