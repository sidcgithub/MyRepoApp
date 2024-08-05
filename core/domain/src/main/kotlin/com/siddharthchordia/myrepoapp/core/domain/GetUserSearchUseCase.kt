package com.siddharthchordia.myrepoapp.core.domain

import com.siddharthchordia.myrepoapp.core.data.UserSearchRepository
import com.siddharthchordia.myrepoapp.core.model.data.SearchResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserSearchUseCase @Inject constructor(
    private val userSearchRepository: UserSearchRepository,
) {
    operator fun invoke(searchQuery: String): Flow<SearchResult> {
        return userSearchRepository.searchContents(searchQuery)
    }
}
