package com.shiraj.searchgithubusers.data.source.remote.mappers

import com.shiraj.searchgithubusers.data.source.remote.response.GithubUserResponse.ItemResponse
import com.shiraj.searchgithubusers.domain.models.User

typealias UserMapperAlias = Mapper<ItemResponse, User>

object UserMapper : UserMapperAlias {
    override fun map(input: ItemResponse) = with(input) {
        User(
            imageUrl = avatarUrl,
            login = login,
            type = type,
        )
    }
}