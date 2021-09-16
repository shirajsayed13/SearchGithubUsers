package com.shiraj.searchgithubusers.features.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.shiraj.searchgithubusers.domain.repositories.SearchUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    searchUserRepository: SearchUserRepository
) : ViewModel() {

    companion object {
        lateinit var searchKeyword: String
    }

    val result = searchUserRepository
        .fetch(searchKeyword)
        .cachedIn(viewModelScope)
}