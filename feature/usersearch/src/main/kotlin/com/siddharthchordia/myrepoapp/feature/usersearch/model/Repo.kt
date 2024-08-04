package com.siddharthchordia.myrepoapp.feature.usersearch.model

data class Repo(
    val name: String,
    val description: String,
    val updatedAt: String,
    val stargazersCount: Long,
    val forks: Long,
)
