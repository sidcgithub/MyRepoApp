package com.siddharthchordia.myrepoapp.core.data.di

import com.siddharthchordia.myrepoapp.core.data.UserSearchRepository
import com.siddharthchordia.myrepoapp.core.data.UserSearchRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindsSearchContentsRepository(
        searchContentsRepository: UserSearchRepositoryImpl,
    ): UserSearchRepository
}
