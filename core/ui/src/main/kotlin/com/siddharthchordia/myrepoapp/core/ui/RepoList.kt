package com.siddharthchordia.myrepoapp.core.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.siddharthchordia.myrepoapp.core.model.data.Repo

@Composable
fun RepoList(searchResultUiState: SearchResultUiState, action: (Repo) -> Unit = {}) {
    var animateRepoList by remember { mutableStateOf(false) }
    when (searchResultUiState) {
        is SearchResultUiState.Loading -> {
            Text(
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen.padding_small,
                    ),
                ),
                text = stringResource(R.string.loading_status),
            )
            animateRepoList = false
        }

        is SearchResultUiState.EmptyQuery -> {
            Text(
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen.padding_small,
                    ),
                ),
                text = searchResultUiState.message,
            )
            animateRepoList = false
        }
        is SearchResultUiState.LoadFailed -> {
            Text(
                modifier = Modifier.padding(
                    horizontal = dimensionResource(
                        id = R.dimen.padding_small,
                    ),
                ),
                text = stringResource(R.string.search_error_status),
            )
            animateRepoList = false
        }

        is SearchResultUiState.Success -> {
            animateRepoList = true
        }
    }
    AnimatedVisibility(
        visible = animateRepoList,
        enter = slideInVertically(
            animationSpec = tween(durationMillis = 1000),
            initialOffsetY = { it / 5 },
        ) + fadeIn(
            tween(1000),
            initialAlpha = 0.0f,
        ),
        exit = slideOutVertically(
            animationSpec = tween(durationMillis = 1000),
        ) + fadeOut(
            tween(1000),
        ),
    ) {
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
            if (searchResultUiState is SearchResultUiState.Success && searchResultUiState.avatarUrl.isNotBlank()) {
                items(searchResultUiState.repoList.size) { index ->
                    val repo = searchResultUiState.repoList[index]
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { action(repo) }
                            .padding(
                                dimensionResource(
                                    id = R.dimen.padding_medium,
                                ),
                            ),
                        shape = RectangleShape,
                        elevation = CardDefaults.cardElevation(dimensionResource(id = R.dimen.elevation_small)),
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
