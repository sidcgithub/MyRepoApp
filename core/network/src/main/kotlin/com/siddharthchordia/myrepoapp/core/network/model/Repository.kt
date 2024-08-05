package com.siddharthchordia.myrepoapp.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Repository(
    @SerialName("name")
    val name: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("updated_at")
    val updatedAt: String?,
    @SerialName("stargazers_count")
    val stargazersCount: Long?,
    @SerialName("forks")
    val forks: Long?,
)
