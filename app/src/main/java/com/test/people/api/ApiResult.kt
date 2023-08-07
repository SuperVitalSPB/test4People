package com.test.people.api

import com.test.people.db.Favorites
import com.test.people.model.LatestRate
import com.test.people.model.Valute

open class ApiResult<out T>(
    val data: T?,
    var errorMessage: String?
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

        fun startFavorites() = ApiResult(emptyList<Favorites>(), null)

        fun startValute() = ApiResult(emptyList<Valute>(), null)
    }
}
