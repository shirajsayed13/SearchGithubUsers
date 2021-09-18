package com.shiraj.searchgithubusers.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.shiraj.searchgithubusers.data.source.paging.GithubUserPagingSource
import com.shiraj.searchgithubusers.data.source.remote.api.GithubApi
import com.shiraj.searchgithubusers.data.source.remote.mappers.UserMapperAlias
import com.shiraj.searchgithubusers.domain.repositories.SearchUserRepository
import javax.inject.Inject

class SearchUserRepositoryImpl @Inject constructor(
    private val api: GithubApi,
    private val mapperAlias: UserMapperAlias,
) : SearchUserRepository {
    override fun fetch(searchKeyword: String) = Pager(
        pagingSourceFactory = { GithubUserPagingSource(api, mapperAlias, searchKeyword) },
        config = PagingConfig(
            pageSize = 9
        )
    ).flow

}