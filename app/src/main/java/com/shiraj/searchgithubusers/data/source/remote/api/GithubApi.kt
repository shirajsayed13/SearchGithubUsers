package com.shiraj.searchgithubusers.data.source.remote.api

import com.shiraj.searchgithubusers.data.source.remote.response.GithubUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("search/users")
    suspend fun fetchGithubUsers(
        @Query("q", encoded = true) query: String,
        @Query("page", encoded = true) page: Int = 1,
        @Query("per_page", encoded = true) size: Int = 9,
    ): GithubUserResponse
}