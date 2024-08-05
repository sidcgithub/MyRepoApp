package com.siddharthchordia.myrepoapp.core.network

import com.siddharthchordia.myrepoapp.core.network.model.Repository
import com.siddharthchordia.myrepoapp.core.network.model.UserProfile
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkDataSource {

    @GET("users/{userId}")
    suspend fun getUserProfile(@Path("userId") userId: String): UserProfile

    @GET("users/{userId}/repos")
    suspend fun getUserRepos(@Path("userId") userId: String): List<Repository>
}
