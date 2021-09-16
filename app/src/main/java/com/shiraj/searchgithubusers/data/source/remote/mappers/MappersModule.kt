package com.shiraj.searchgithubusers.data.source.remote.mappers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MappersModule {

    @Provides
    fun provideUserMapper(): UserMapperAlias = UserMapper
}