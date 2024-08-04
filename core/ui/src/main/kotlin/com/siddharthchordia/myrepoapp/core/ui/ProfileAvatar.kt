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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProfileAvatar(searchResultUiState: SearchResultUiState) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        if (searchResultUiState is SearchResultUiState.Success) {
            Image(
                painter = rememberAsyncImagePainter(searchResultUiState.avatarUrl),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.image_large)),
            )
            Text(
                text = "Username",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_medium)),
                textAlign = TextAlign.Center,
            )
        }
    }
}
