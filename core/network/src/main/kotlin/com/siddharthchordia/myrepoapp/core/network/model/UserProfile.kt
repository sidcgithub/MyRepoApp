package com.siddharthchordia.myrepoapp.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(@SerialName("name") val name: String?, @SerialName("avatar_url") val avatarUrl: String?)
