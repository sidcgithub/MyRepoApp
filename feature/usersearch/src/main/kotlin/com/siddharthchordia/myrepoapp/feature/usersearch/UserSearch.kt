package com.siddharthchordia.myrepoapp.feature.usersearch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.siddharthchordia.myrepoapp.core.ui.ProfileAvatar
import com.siddharthchordia.myrepoapp.core.ui.R
import com.siddharthchordia.myrepoapp.core.ui.RepoList
import com.siddharthchordia.myrepoapp.core.ui.UserSearchComponent

@Composable
fun UserSearch(viewModel: UserSearchViewModel, innerPadding: PaddingValues) {
    val searchQuery = remember { mutableStateOf("") }
    val searchResultUiState = viewModel.searchResultUiState.collectAsStateWithLifecycle()
    Box(
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxWidth(),
    ) {
        UserSearchComponent(
            searchQuery = searchQuery.value,
            searchResultUiState = searchResultUiState.value,
            onSearchQueryChanged = { newValue -> searchQuery.value = newValue },
            onSearchButtonClicked = { viewModel.onSearchQueryChanged(searchQuery.value) },
        ) {
            Column(Modifier.fillMaxSize()) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = R.dimen.padding_medium)),
                    verticalAlignment = CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    ProfileAvatar(searchResultUiState.value)
                }
                RepoList(searchResultUiState.value)
            }
        }
    }
}
