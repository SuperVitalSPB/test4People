package com.test.people.di

import com.test.people.api.ApiResult
import com.test.people.model.ErrorResponse
import com.test.people.model.LatestRate
import javax.inject.Inject

class InteractorEntity @Inject constructor(private val networkUtils: NetworkUtils) {

    suspend fun getLatest(): ApiResult<LatestRate> {
        val result = networkUtils.retrofit.getLatest()
        result.errorBody()?.string()?.let {
            return ApiResult.Error(ErrorResponse().from(it)?.message ?: "Not response")
        }
        return ApiResult.Success(result.body()?.let { LatestRate.from(it) })
    }
}