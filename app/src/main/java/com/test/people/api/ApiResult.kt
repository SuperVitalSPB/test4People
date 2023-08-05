package com.test.people.api

import com.test.people.model.LatestRate

open class ApiResult<out T>(
    val data: T?,
    val errorMessage: String?
) {
    class Success<out T>(_data: T?) : ApiResult<T>(
        data = _data,
        errorMessage = null
    )

    class Error<out T>(exception: String) : ApiResult<T>(
        data = null,
        errorMessage = exception
    )

    companion object {
        fun startValue() = ApiResult(LatestRate(), null)
    }
}
