package com.siddharthchordia.myrepoapp.feature.usersearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siddharthchordia.myrepoapp.core.model.data.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import xom.siddharthchordia.myrepoapp.core.ui.SearchResultUiState
import javax.inject.Inject

@HiltViewModel
class UserSearchViewModel @Inject constructor() : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResultUiState =
        MutableStateFlow<SearchResultUiState>(SearchResultUiState.EmptyQuery)
    val searchResultUiState: StateFlow<SearchResultUiState> = _searchResultUiState

    fun onSearchUpdateButtonClicked(query: String) {
        viewModelScope.launch {
            _searchResultUiState.value = SearchResultUiState.Loading
            updateSearchQuery(query)
            if (_searchQuery.value.isNotBlank()) {
                delay(1000)

                // Return dummy data
                _searchResultUiState.value = SearchResultUiState.Success(
                    avatarUrl = "https://avatars.githubusercontent.com/u/583231?v=4",
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
                        Repo(
                            "Repo 3",
                            "Description 3",
                            "2022-01-03",
                            300,
                            70,
                        ),
                        Repo(
                            "Repo 4",
                            "Description 4",
                            "2022-01-04",
                            400,
                            80,
                        ),
                        Repo(
                            "Repo 5",
                            "Description 5",
                            "2022-01-05",
                            500,
                            90,
                        ),
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
}
