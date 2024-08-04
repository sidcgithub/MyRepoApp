package xom.siddharthchordia.myrepoapp.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
                    Card(modifier = Modifier.padding(16.dp)) {
                        Column {
                            Text(
                                text = "Repo: ${repo.name}",
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(16.dp),
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = "Description: ${repo.description}, Updated At: ${repo.updatedAt}, Stars: ${repo.stargazersCount}, Forks: ${repo.forks}",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(16.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}
