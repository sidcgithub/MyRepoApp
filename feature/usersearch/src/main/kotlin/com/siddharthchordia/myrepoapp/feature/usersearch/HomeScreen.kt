package com.siddharthchordia.myrepoapp.feature.usersearch

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.siddharthchordia.myrepoapp.core.model.data.Repo
import com.siddharthchordia.myrepoapp.core.ui.ProfileAvatar
import com.siddharthchordia.myrepoapp.core.ui.R
import com.siddharthchordia.myrepoapp.core.ui.RepoList
import com.siddharthchordia.myrepoapp.core.ui.SearchResultUiState
import com.siddharthchordia.myrepoapp.core.ui.UserSearchComponent
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@Composable
fun HomeScreen(
    viewModel: UserSearchViewModel,
    navigate: () -> Unit,
    innerPadding: PaddingValues,
) {
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
            onResultDisplaySelectAction = { repo ->
                viewModel.onRepoSelected(repo)
                navigate()
            },
        ) { searchResultUiState, action ->
            SearchResultComponent(searchResultUiState, action)
        }
    }
}

@Composable
fun SearchResultComponent(uiState: SearchResultUiState, widgetAction: (Repo) -> Unit) {
    Column(Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = (uiState is SearchResultUiState.Success && uiState.avatarUrl.isNotBlank()),
            enter = slideInVertically(
                animationSpec = tween(durationMillis = 1000),
                initialOffsetY = { it / 5 },
            ) + fadeIn(
                tween(1000),
                initialAlpha = 0.0f,
            ),
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_medium)),
                verticalAlignment = CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                ProfileAvatar(uiState)
            }
        }

        RepoList(uiState, widgetAction)
    }
}

@Preview
@Composable
fun PreviewSearchResultComponent() {
    SearchResultComponent(
        SearchResultUiState.Success(
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            username = "randomusername",
            repoList = listOf(
                Repo(
                    name = "repository 1",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                    forks = 303,
                    updatedAt = "2021-09-01T00:00:00Z",
                    stargazersCount = 303,
                ),
                Repo(
                    name = "repository 2",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                    forks = 303,
                    updatedAt = "2021-09-01T00:00:00Z",
                    stargazersCount = 303,
                ),
                Repo(
                    name = "repository 3",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                    forks = 303,
                    updatedAt = "2021-09-01T00:00:00Z",
                    stargazersCount = 303,
                ),
            ),
        ),
        widgetAction = {},
    )
}

class Test @Inject constructor(
    private val repoDetailsFlow: MutableStateFlow<Repo?>,
) {
    fun test() {
        println("test")
    }
}
