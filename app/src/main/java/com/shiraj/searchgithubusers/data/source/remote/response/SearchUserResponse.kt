package com.shiraj.searchgithubusers.data.source.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GithubUserResponse(
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean,
    @Json(name = "items")
    val items: List<ItemResponse>,
    @Json(name = "total_count")
    val totalCount: Int
) {
    data class ItemResponse(
        val login: String,
        val id: Long,
        val type: String,
        @Json(name = "avatar_url")
        val avatarUrl: String,
    )
}



