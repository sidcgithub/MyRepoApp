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

    @BeforeTest
    fun setup() {
        repoDetailsFlow = MutableStateFlow(null)
        viewModel = UserSearchViewModel(useCase, repoDetailsFlow, SavedStateHandle())
    }

    @Test
    fun `initial state is EmptyQuery`() {
        assertEquals(SearchResultUiState.EmptyQuery, viewModel.searchResultUiState.value)
    }

    @Test
    fun `onSearchUpdateButtonClicked with empty query keeps state as EmptyQuery`() = runTest {
        viewModel.onSearchQueryChanged("")
        assertEquals(SearchResultUiState.EmptyQuery, viewModel.searchResultUiState.value)
    }

    @Test
    fun `onSearchUpdateButtonClicked with non-empty query sets state to Loading then Success`() =
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

            viewModel.onSearchQueryChanged("query")

            val collectJob = launch(UnconfinedTestDispatcher()) {
                viewModel.searchResultUiState.toList(states)
            }

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
