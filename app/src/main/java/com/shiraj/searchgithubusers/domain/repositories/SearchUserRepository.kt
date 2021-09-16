package com.shiraj.searchgithubusers.domain.repositories

import androidx.paging.PagingData
import com.shiraj.searchgithubusers.domain.models.User
import kotlinx.coroutines.flow.Flow

interface SearchUserRepository {

    fun fetch(searchKeyword: String): Flow<PagingData<User>>
}