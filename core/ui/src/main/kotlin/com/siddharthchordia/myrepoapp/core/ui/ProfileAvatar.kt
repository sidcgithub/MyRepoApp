package com.siddharthchordia.myrepoapp.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProfileAvatar(searchResultUiState: SearchResultUiState) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (searchResultUiState is SearchResultUiState.Success) {
            Image(
                painter = rememberAsyncImagePainter(searchResultUiState.avatarUrl),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(100.dp),
            )
            Text(text = "Username", modifier = Modifier.fillMaxWidth().padding(16.dp), textAlign = TextAlign.Center)
        }
    }
}
