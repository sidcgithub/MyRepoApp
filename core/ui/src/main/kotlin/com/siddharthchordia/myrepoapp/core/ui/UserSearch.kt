package com.siddharthchordia.myrepoapp.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.siddharthchordia.myrepoapp.core.model.data.Repo

@Composable
fun UserSearchComponent(
    searchQuery: String,
    searchResultUiState: SearchResultUiState,
    onSearchQueryChanged: (String) -> Unit,
    onSearchButtonClicked: () -> Unit,
    onResultDisplaySelectAction: (Repo) -> Unit = {},
    searchResultWidget: @Composable (SearchResultUiState, widgetAction: (Repo) -> Unit) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,

        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(0.7f),
                value = searchQuery,
                onValueChange = onSearchQueryChanged,
                label = { Text(stringResource(R.string.search_label)) },
            )

            Button(
                onClick = {
                    onSearchButtonClicked()
                    keyboardController?.hide()
                },
            ) {
                Text(stringResource(R.string.search_button_label))
            }
        }

        searchResultWidget(searchResultUiState, onResultDisplaySelectAction)
    }
}

sealed interface SearchResultUiState {
    data object Loading : SearchResultUiState
    data class EmptyQuery(val message: String = "Please enter a search query") : SearchResultUiState

    data object LoadFailed : SearchResultUiState

    data class Success(
        val avatarUrl: String = "",
        val username: String = "",
        val repoList: List<Repo> = emptyList(),
    ) : SearchResultUiState {
        fun isEmpty(): Boolean = avatarUrl.isBlank() && repoList.isEmpty() && username.isBlank()
    }
}

@Preview(showBackground = true)
@Composable
fun UserSearchPreview() {
    UserSearchComponent(
        searchQuery = "Hello",
        searchResultUiState = SearchResultUiState.Success(
            avatarUrl = "https://example.com/avatar.png",
            repoList = listOf(
                Repo(
                    "Repo 1",
                    "Description 1",
                    "2022-01-01",
                    100,
                    50,
                ),
                Repo(
                    "Repo 2",
                    "Description 2",
                    "2022-01-02",
                    200,
                    60,
                ),
            ),
        ),
        onSearchQueryChanged = {},
        onSearchButtonClicked = {},
        onResultDisplaySelectAction = { },
    ) { _, _ ->
        Box {
            Text("Search Result")
        }
    }
}
