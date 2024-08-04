package com.siddharthchordia.myrepoapp.core.model.data

data class Repo(
    val name: String,
    val description: String,
    val updatedAt: String,
    val stargazersCount: Long,
    val forks: Long,
)
