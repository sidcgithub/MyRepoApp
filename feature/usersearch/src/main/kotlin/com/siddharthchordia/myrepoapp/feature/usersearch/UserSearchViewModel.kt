package com.siddharthchordia.myrepoapp.feature.usersearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siddharthchordia.myrepoapp.feature.usersearch.model.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserSearchViewModel @Inject constructor() : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResultUiState = MutableStateFlow<SearchResultUiState>(SearchResultUiState.EmptyQuery)
    val searchResultUiState: StateFlow<SearchResultUiState> = _searchResultUiState

    fun onSearchUpdateButtonClicked(query: String) {
        viewModelScope.launch {
            _searchResultUiState.value = SearchResultUiState.Loading
            delay(1000)
            updateSearchQuery(query)
            if (_searchQuery.value.length >= SEARCH_QUERY_MIN_LENGTH) {
                // Return dummy data
                _searchResultUiState.value = SearchResultUiState.Success(
                    avatarUrl = "https://example.com/avatar.png",
                    repoList = listOf(
                        Repo("Repo 1", "Description 1", "2022-01-01", 100, 50),
                        Repo("Repo 2", "Description 2", "2022-01-02", 200, 60),
                    ),
                )
            } else {
                _searchResultUiState.value = SearchResultUiState.EmptyQuery
            }
        }
    }

    private fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    companion object {
        private const val SEARCH_QUERY_MIN_LENGTH = 3
    }
}
