package com.siddharthchordia.myrepoapp.feature.usersearch

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siddharthchordia.myrepoapp.core.domain.GetUserSearchUseCase
import com.siddharthchordia.myrepoapp.core.ui.SearchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class UserSearchViewModel @Inject constructor(
    private val getUserSearchUseCase: GetUserSearchUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    companion object {
        private const val SEARCH_QUERY = "search_query"
    }

    val searchQuery = savedStateHandle.getStateFlow(key = SEARCH_QUERY, initialValue = "")

    val searchResultUiState: StateFlow<SearchResultUiState> = searchQuery
        .flatMapLatest { query: String ->
            if (query.isBlank()) {
                flowOf(SearchResultUiState.EmptyQuery)
            } else {

                getUserSearchUseCase(query)
                    .map { data ->
                        if (data.username.isBlank() && data.repoList.isEmpty()) {
                            SearchResultUiState.LoadFailed
                        } else {
                            SearchResultUiState.Success(
                                avatarUrl = data.avatarUrl,
                                username = data.username,
                                repoList = data.repoList,
                            )
                        }
                    }
            }
                .catch { emit(SearchResultUiState.LoadFailed) }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SearchResultUiState.EmptyQuery,
        )

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }
}
