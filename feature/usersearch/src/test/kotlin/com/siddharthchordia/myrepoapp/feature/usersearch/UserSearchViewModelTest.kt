package com.siddharthchordia.myrepoapp.feature.usersearch

import com.siddharthchordia.myrepoapp.core.testing.MainDispatcherRule
import com.siddharthchordia.myrepoapp.core.ui.SearchResultUiState
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class UserSearchViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val viewModel = UserSearchViewModel()

    @Test
    fun `initial state is EmptyQuery`() {
        assertEquals(SearchResultUiState.EmptyQuery, viewModel.searchResultUiState.value)
    }

    @Test
    fun `onSearchUpdateButtonClicked with empty query keeps state as EmptyQuery`() = runTest {
        viewModel.onSearchUpdateButtonClicked("")
        assertEquals(SearchResultUiState.EmptyQuery, viewModel.searchResultUiState.value)
    }

    @Test
    fun `onSearchUpdateButtonClicked with non-empty query sets state to Loading then Success`() = runTest {
        viewModel.onSearchUpdateButtonClicked("query")
        assertEquals(SearchResultUiState.Loading, viewModel.searchResultUiState.value)
        advanceTimeBy(2000)
        assert(viewModel.searchResultUiState.value is SearchResultUiState.Success)
    }

    @Test
    fun `onSearchUpdateButtonClicked updates searchQuery state`() = runTest {
        viewModel.onSearchUpdateButtonClicked("query")
        assertEquals("query", viewModel.searchQuery.value)
    }
}
