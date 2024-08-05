package com.siddharthchordia.myrepoapp.core.network.di

import com.siddharthchordia.myrepoapp.core.network.NetworkDataSource
import com.siddharthchordia.myrepoapp.core.network.retrofit.RetrofitNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor(),
            )
            .build()

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    fun provideNetworkDataSource(
        okhttpCallFactory: dagger.Lazy<Call.Factory>,
        networkJson: Json,
    ): NetworkDataSource {
        return RetrofitNetwork(okhttpCallFactory, networkJson)
    }
}
