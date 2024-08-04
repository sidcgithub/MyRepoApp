package com.siddharthchordia.myrepoapp.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.siddharthchordia.myrepoapp.core.model.data.Repo

@Composable
fun RepoList(searchResultUiState: SearchResultUiState) {
    when (searchResultUiState) {
        is SearchResultUiState.Loading -> Text(modifier = Modifier.padding(horizontal = 8.dp), text = "Loading...")
        is SearchResultUiState.EmptyQuery -> Text(modifier = Modifier.padding(horizontal = 8.dp), text = "Please enter a search query.")
        is SearchResultUiState.LoadFailed -> Text(modifier = Modifier.padding(horizontal = 8.dp), text = "Failed to load search results.")
        is SearchResultUiState.Success -> {
            LazyColumn(contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)) {
                items(searchResultUiState.repoList.size) { index ->
                    val repo = searchResultUiState.repoList[index]
                    Card(modifier = Modifier.fillMaxWidth().padding(16.dp), elevation = CardDefaults.cardElevation(8.dp)) {
                        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                            Text(
                                text = repo.name,
                                style = MaterialTheme.typography.titleMedium,
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = repo.description,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(horizontal = 16.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepoListPreview() {
    val repos = listOf(
        Repo(
            "Repo 1",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            "2022-01-01",
            100,
            50,
        ),
        Repo(
            "Repo 2",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            "2022-01-02",
            200,
            60,
        ),
    )
    val searchResultUiState = SearchResultUiState.Success(
        avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
        repoList = repos,
    )
    RepoList(searchResultUiState)
}
