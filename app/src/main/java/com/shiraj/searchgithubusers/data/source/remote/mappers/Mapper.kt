package com.shiraj.searchgithubusers.data.source.remote.mappers

interface Mapper<T, R> {
    fun map(input: T): R
}