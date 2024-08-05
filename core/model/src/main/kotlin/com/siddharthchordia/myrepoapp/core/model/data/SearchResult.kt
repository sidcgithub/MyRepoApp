package com.siddharthchordia.myrepoapp.core.model.data

data class SearchResult(
    val repoList: List<Repo> = emptyList(),
    val username: String = "",
    val avatarUrl: String = "",
)
