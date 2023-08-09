package com.test.people.repository

import com.test.people.di.NetworkUtils
import javax.inject.Inject

class RepositoryService @Inject constructor(private val networkUtils: NetworkUtils) {

    suspend fun getLatest() =
        networkUtils.retrofit.getLatest()
}