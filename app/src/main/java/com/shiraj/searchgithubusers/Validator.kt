package com.shiraj.searchgithubusers

object Validator {
    fun validateInput(searchKeyword: String): Boolean {
        return (searchKeyword.isNotEmpty())
    }
}