package com.siddharthchordia.myrepoapp.feature.usersearch

import com.siddharthchordia.myrepoapp.feature.usersearch.model.Repo

sealed interface SearchResultUiState {
    data object Loading : SearchResultUiState
    data object EmptyQuery : SearchResultUiState

    data object LoadFailed : SearchResultUiState

    data class Success(
        val avatarUrl: String = "",
        val repoList: List<Repo> = emptyList(),
    ) : SearchResultUiState {
        fun isEmpty(): Boolean = avatarUrl.isBlank() && repoList.isEmpty()
    }
}
