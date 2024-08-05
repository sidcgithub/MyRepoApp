package com.siddharthchordia.myrepoapp.core.ui

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.siddharthchordia.myrepoapp.core.model.data.Repo

@Composable
fun RepoList(searchResultUiState: SearchResultUiState, action: (Repo) -> Unit = {}) {
    when (searchResultUiState) {
        is SearchResultUiState.Loading -> Text(
            modifier = Modifier.padding(
                horizontal = dimensionResource(
                    id = R.dimen.padding_small,
                ),
            ),
            text = stringResource(R.string.loading_status),
        )

        is SearchResultUiState.EmptyQuery -> Text(
            modifier = Modifier.padding(
                horizontal = dimensionResource(
                    id = R.dimen.padding_small,
                ),
            ),
            text = stringResource(R.string.empty_query_error),
        )

        is SearchResultUiState.LoadFailed -> Text(
            modifier = Modifier.padding(
                horizontal = dimensionResource(
                    id = R.dimen.padding_small,
                ),
            ),
            text = stringResource(R.string.search_error_status),
        )

        is SearchResultUiState.Success -> {
            LazyColumn(
                contentPadding = PaddingValues(
                    horizontal = dimensionResource(
                        id = R.dimen.padding_small,
                    ),
                    vertical = dimensionResource(
                        id = R.dimen.padding_small,
                    ),
                ),
            ) {
                items(searchResultUiState.repoList.size) { index ->
                    val repo = searchResultUiState.repoList[index]
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { action(repo) }
                            .padding(
                                dimensionResource(
                                    id = R.dimen.padding_medium,
                                ),
                            ),
                        elevation = CardDefaults.cardElevation(
                            dimensionResource(
                                id = R.dimen.padding_small,
                            ),
                        ),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    dimensionResource(
                                        id = R.dimen.padding_medium,
                                    ),
                                ),
                        ) {
                            Text(
                                text = repo.name,
                                style = MaterialTheme.typography.titleMedium,
                            )
                            Spacer(
                                modifier = Modifier.height(
                                    dimensionResource(
                                        id = R.dimen.padding_medium,
                                    ),
                                ),
                            )
                            Text(
                                text = repo.description,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(
                                    horizontal = dimensionResource(
                                        id = R.dimen.padding_medium,
                                    ),
                                ),
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
