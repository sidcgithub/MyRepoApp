package com.siddharthchordia.myrepoapp.feature.repodetails

import com.siddharthchordia.myrepoapp.core.model.data.Repo
import com.siddharthchordia.myrepoapp.core.model.data.RepoDetails
import com.siddharthchordia.myrepoapp.core.testing.MainDispatcherRule
import com.siddharthchordia.myrepoapp.core.ui.RepoDetailsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import kotlin.test.BeforeTest

class RepoDetailsViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repoDetailsFlow: MutableStateFlow<RepoDetails?>
    private lateinit var viewModel: RepoDetailsViewModel

    @BeforeTest
    fun setup() {
        repoDetailsFlow = MutableStateFlow(null)
        viewModel = RepoDetailsViewModel(repoDetailsFlow)
    }

    @Test
    fun `initial state is Loading`() = runTest {
        assertEquals(RepoDetailsUiState.Loading, viewModel.repoDetailsUiState.value)
    }

    @Test
    fun `repoDetailsFlow update sets state to Success`() = runTest {
        val states = mutableListOf<RepoDetailsUiState>()
        val collectJob = launch(StandardTestDispatcher(testScheduler)) {
            viewModel.repoDetailsUiState.toList(states)
        }

        val repo = Repo("name", "description", "1", 1, 3)
        val repoDetails = RepoDetails(repo, 1)
        repoDetailsFlow.value = repoDetails

        advanceUntilIdle()

        assert(states.last() is RepoDetailsUiState.Success)

        collectJob.cancel()
    }
}
