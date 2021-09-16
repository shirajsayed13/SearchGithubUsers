package com.shiraj.searchgithubusers.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shiraj.searchgithubusers.data.source.remote.api.GithubApi
import com.shiraj.searchgithubusers.data.source.remote.mappers.UserMapperAlias
import com.shiraj.searchgithubusers.domain.models.User

private const val INITIAL_PAGE = 1

class GithubUserPagingSource(
    private val api: GithubApi,
    private val mapper: UserMapperAlias,
    private val searchKeyword: String
) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val page = params.key ?: INITIAL_PAGE
            val response = api.fetchGithubUsers(searchKeyword, page, params.loadSize)
            LoadResult.Page(
                data = response.items.map(mapper::map),
                prevKey = if (page == INITIAL_PAGE) null else page - 1,
                nextKey = if (response.items.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}