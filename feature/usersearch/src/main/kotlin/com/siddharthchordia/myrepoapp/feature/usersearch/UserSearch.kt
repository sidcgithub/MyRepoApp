package com.siddharthchordia.myrepoapp.feature.usersearch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.siddharthchordia.myrepoapp.feature.usersearch.model.Repo

/**
 * The following implementation creates a search composable with an TextField and a Button.
 * The composable is broken down into a stateful composable and a stateless composable.
 */
@Composable
fun UserSearch(viewModel: UserSearchViewModel, innerPadding: PaddingValues) {
    val searchQuery = remember { mutableStateOf("") }
    val searchResultUiState = viewModel.searchResultUiState.collectAsStateWithLifecycle()
    Box(modifier = Modifier.padding(innerPadding).fillMaxWidth()) {
        UserSearch(
            searchQuery = searchQuery.value,
            searchResultUiState = searchResultUiState.value,
            onSearchQueryChanged = { newValue -> searchQuery.value = newValue },
            onSearchButtonClicked = { viewModel.onSearchUpdateButtonClicked(searchQuery.value) },
        )
    }
}

@Composable
fun UserSearch(
    searchQuery: String,
    searchResultUiState: SearchResultUiState,
    onSearchQueryChanged: (String) -> Unit,
    onSearchButtonClicked: () -> Unit,
) {
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
                label = { Text("Search Query") },
            )

            Button(onClick = onSearchButtonClicked) {
                Text("Search")
            }
        }

        when (searchResultUiState) {
            is SearchResultUiState.Loading -> Text("Loading...")
            is SearchResultUiState.EmptyQuery -> Text("Please enter a search query.")
            is SearchResultUiState.LoadFailed -> Text("Failed to load search results.")
            is SearchResultUiState.Success -> {
                Text("Avatar URL: ${searchResultUiState.avatarUrl}")
                searchResultUiState.repoList.forEach { repo ->
                    Text("Repo: ${repo.name}, Description: ${repo.description}, Updated At: ${repo.updatedAt}, Stars: ${repo.stargazersCount}, Forks: ${repo.forks}")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserSearchPreview() {
    UserSearch(
        searchQuery = "Hello",
        searchResultUiState = SearchResultUiState.Success(
            avatarUrl = "https://example.com/avatar.png",
            repoList = listOf(
                Repo("Repo 1", "Description 1", "2022-01-01", 100, 50),
                Repo("Repo 2", "Description 2", "2022-01-02", 200, 60),
            ),
        ),
        onSearchQueryChanged = {},
        onSearchButtonClicked = {},
    )
}
