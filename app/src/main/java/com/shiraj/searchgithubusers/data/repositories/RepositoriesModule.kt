package com.shiraj.searchgithubusers.data.repositories

import com.shiraj.searchgithubusers.domain.repositories.SearchUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindSearchUserRepository(impl: SearchUserRepositoryImpl): SearchUserRepository

}