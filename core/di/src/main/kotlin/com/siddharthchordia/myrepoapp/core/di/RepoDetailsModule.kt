package com.siddharthchordia.myrepoapp.core.di

import com.siddharthchordia.myrepoapp.core.model.data.RepoDetails
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoDetailsModule {
    @Provides
    @Singleton
    fun provideSelectedRepoFlow(): MutableStateFlow<RepoDetails?> = MutableStateFlow(null)
}
