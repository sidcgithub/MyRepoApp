package com.siddharthchordia.myrepoapp.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProfileAvatar(searchResultUiState: SearchResultUiState) {
    Column {
        if (searchResultUiState is SearchResultUiState.Success) {
            Image(
                painter = rememberAsyncImagePainter(searchResultUiState.avatarUrl),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(100.dp),
            )
        }
    }
}
