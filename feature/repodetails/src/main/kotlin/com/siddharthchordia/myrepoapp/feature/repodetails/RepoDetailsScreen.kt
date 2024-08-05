package com.siddharthchordia.myrepoapp.feature.repodetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.siddharthchordia.myrepoapp.core.ui.RepoDetailsComponent
import com.siddharthchordia.myrepoapp.core.ui.RepoDetailsUiState

@Composable
fun RepoDetailScreen(repoDetailsViewModel: RepoDetailsViewModel, innerPadding: PaddingValues) {
    val repoDetailsUiState = repoDetailsViewModel.repoDetailsUiState.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
    ) {
        RepoDetailScreen(repoDetailsUiState.value)
    }
}

@Composable
fun RepoDetailScreen(repoDetailsUiState: RepoDetailsUiState) {
    when (repoDetailsUiState) {
        is RepoDetailsUiState.Success -> {
            RepoDetailsComponent(repoDetailsUiState)
        }
        RepoDetailsUiState.LoadFailed -> {
            Text(text = "Failed to load Repo Details")
        }

        RepoDetailsUiState.Loading -> {
            Text(text = "Loading Repo Details")
        }
    }
}
