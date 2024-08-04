package com.siddharthchordia.myrepoapp.core.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.siddharthchordia.myrepoapp.core.model.data.Repo
import com.siddharthchordia.myrepoapp.core.network.NetworkDataSource
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitNetworkApi {
    @GET("users/{userId}")
    suspend fun getUserProfile(@Path("userId") userId: String): Any

    @GET("users/{userId}/repos")
    suspend fun getUserRepos(@Path("userId") userId: String): List<Repo>
}

@Singleton
internal class RetrofitNetwork @Inject constructor(
    networkJson: Json,
    okhttpCallFactory: dagger.Lazy<Call.Factory>,
) : NetworkDataSource {

    private val networkApi =
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
            .create(RetrofitNetworkApi::class.java)

    override suspend fun getUserProfile(userId: String): Any =
        networkApi.getUserProfile(userId)

    override suspend fun getUserRepos(userId: String): List<Repo> =
        networkApi.getUserRepos(userId)
}
