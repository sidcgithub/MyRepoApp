package com.siddharthchordia.myrepoapp.feature.usersearch

import androidx.lifecycle.SavedStateHandle
import com.siddharthchordia.myrepoapp.core.domain.GetUserSearchUseCase
import com.siddharthchordia.myrepoapp.core.model.data.Repo
import com.siddharthchordia.myrepoapp.core.model.data.RepoDetails
import com.siddharthchordia.myrepoapp.core.model.data.SearchResult
import com.siddharthchordia.myrepoapp.core.testing.MainDispatcherRule
import com.siddharthchordia.myrepoapp.core.ui.SearchResultUiState
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import kotlin.test.BeforeTest

class UserSearchViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase = mockk<GetUserSearchUseCase>()
    private lateinit var repoDetailsFlow: MutableStateFlow<RepoDetails?>
    private lateinit var viewModel: UserSearchViewModel
    val savedStateHandle = SavedStateHandle()

    @BeforeTest
    fun setup() {
        repoDetailsFlow = MutableStateFlow(null)
        viewModel = UserSearchViewModel(useCase, repoDetailsFlow, savedStateHandle)
    }

    @Test
    fun `initial state is EmptyQuery`() {
        assert(viewModel.searchResultUiState.value is SearchResultUiState.EmptyQuery)
    }

    @Test
    fun `onSearchUpdateButtonClicked with empty query keeps state as EmptyQuery`() = runTest {
        viewModel.onSearchQueryChanged("")
        assert(viewModel.searchResultUiState.value is SearchResultUiState.EmptyQuery)
    }

    /**
     * This test function verifies that when the search button is clicked with a non-empty query,
     * the state of the search results UI is eventually set to Success.
     *
     * The function first creates a mock flow of SearchResult objects, which is returned when the
     * GetUserSearchUseCase is invoked. It then collects the states emitted by the searchResultUiState
     * flow into a list. After updating the search query, the function waits until all pending tasks
     * in the test coroutine scope have completed.
     *
     * Finally, the function asserts that the last state in the list is a Success state, indicating that
     * the search operation completed successfully.
     */
    @Test
    fun `onSearchUpdateButtonClicked with non-empty query sets state to Success finally`() =
        runTest {
            val mockResultFlow = flow {
                emit(
                    SearchResult(
                        username = "username",
                        avatarUrl = "avatarUrl",
                        repoList = listOf(Repo("name", "description", "1", 1, 3)),
                    ),
                )
            }

            every { useCase(any<String>()) } returns mockResultFlow

            val states = mutableListOf<SearchResultUiState>()
            val collectJob = launch(UnconfinedTestDispatcher()) {
                viewModel.searchResultUiState.toList(states)
            }
            viewModel.onSearchQueryChanged("query")
            advanceUntilIdle()

            assert(states.last() is SearchResultUiState.Success)

            collectJob.cancel()
        }

    @Test
    fun `onSearchUpdateButtonClicked updates searchQuery state`() = runTest {
        viewModel.onSearchQueryChanged("query")
        assertEquals("query", viewModel.searchQuery.value)
    }

    @Test
    fun `onRepoSelected updates repoDetailsFlow`() = runTest {
        val repo = Repo("name", "description", "1", 1, 3)
        viewModel.onRepoSelected(repo)
        val repoDetails = repoDetailsFlow.value
        assertEquals(repo, repoDetails?.repo)
        assertEquals(viewModel.mutableTotalForks.value, repoDetails?.totalForks)
    }
}
