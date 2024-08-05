package com.siddharthchordia.myrepoapp.core.data

import com.siddharthchordia.myrepoapp.core.model.data.SearchResult
import kotlinx.coroutines.flow.Flow

interface UserSearchRepository {
    fun searchContents(searchQuery: String): Flow<SearchResult>
}
