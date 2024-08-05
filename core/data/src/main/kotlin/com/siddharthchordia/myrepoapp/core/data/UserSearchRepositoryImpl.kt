package com.siddharthchordia.myrepoapp.core.data

import com.siddharthchordia.myrepoapp.core.model.data.Repo
import com.siddharthchordia.myrepoapp.core.model.data.SearchResult
import com.siddharthchordia.myrepoapp.core.network.NetworkDataSource
import com.siddharthchordia.myrepoapp.core.network.model.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserSearchRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
) : UserSearchRepository {

    override fun searchContents(searchQuery: String): Flow<SearchResult> {
        // Flow for user profile
        val userProfileFlow = flow {
            val userProfile = networkDataSource.getUserProfile(searchQuery)
            emit(SearchResult(username = userProfile.name ?: "", avatarUrl = userProfile.avatarUrl ?: ""))
        }.catch { emit(SearchResult()) } // Emit empty state on error

        // Flow for repo list
        val userRepoFlow = flow {
            val repoList = networkDataSource.getUserRepos(searchQuery)

            emit(SearchResult(repoList = mapRepositoryToDomainRepo(repoList)))
        }.catch { emit(SearchResult()) } // Emit empty state on error

        // Combine the flows to update the SearchResult accordingly
        return combine(userProfileFlow, userRepoFlow) { userProfile, repoList ->
            SearchResult(
                username = userProfile.username,
                avatarUrl = userProfile.avatarUrl,
                repoList = repoList.repoList,
            )
        }
    }

    private fun mapRepositoryToDomainRepo(repoList: List<Repository>) =
        repoList.map {
            Repo(
                name = it.name ?: "",
                updatedAt = it.updatedAt ?: "",
                stargazersCount = it.stargazersCount ?: 0,
                forks = it.forks ?: 0,
                description = it.description ?: "",
            )
        }
}
