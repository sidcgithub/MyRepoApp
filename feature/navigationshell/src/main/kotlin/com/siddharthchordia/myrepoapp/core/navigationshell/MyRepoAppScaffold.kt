package com.siddharthchordia.myrepoapp.core.navigationshell

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun MyRepoAppScaffold(screenNavParams: ScreenNavParams, screen: @Composable (PaddingValues) -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        screen(innerPadding)
    }
}

data class ScreenNavParams(val navController: NavController, val backNavigation: () -> Unit = {})
