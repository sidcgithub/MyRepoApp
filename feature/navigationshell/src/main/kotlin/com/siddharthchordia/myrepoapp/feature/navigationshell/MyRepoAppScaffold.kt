package com.siddharthchordia.myrepoapp.feature.navigationshell

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyRepoAppScaffold(screenNavParams: ScreenNavParams, screen: @Composable (PaddingValues) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.take_home_title)) },
                navigationIcon = {
                    if (screenNavParams is ScreenNavParams.BackEnabled) {
                        IconButton(onClick = { screenNavParams.backNav() }) {
                            Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                },
            )
        },
    ) { innerPadding ->
        screen(innerPadding)
    }
}

sealed class ScreenNavParams(open val navController: NavController) {
    data class BackEnabled(override val navController: NavController, val backNav: () -> Unit) : ScreenNavParams(navController)
    data class NoBack(override val navController: NavController) : ScreenNavParams(navController)
}
