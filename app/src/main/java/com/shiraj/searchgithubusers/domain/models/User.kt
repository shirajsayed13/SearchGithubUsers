package com.shiraj.searchgithubusers.domain.models

data class User(
    val login: String,
    val imageUrl: String,
    val type: String,
)
