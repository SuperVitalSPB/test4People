package com.test.people.interactor

import com.test.people.api.ApiResult
import com.test.people.db.Favorites
import com.test.people.di.DatabaseHelper
import com.test.people.di.NetworkUtils
import com.test.people.model.ErrorResponse
import com.test.people.model.LatestRate
import javax.inject.Inject

class InteractorEntity @Inject constructor(private val networkUtils: NetworkUtils,
                                           private val interactorDatabase: InteractorDatabase
) {

    suspend fun getLatest(): ApiResult<LatestRate> {
        val result = networkUtils.retrofit.getLatest()
        result.errorBody()?.string()?.let {
            return ApiResult.Error(ErrorResponse().from(it)?.message ?: "Not response")
        }
        result.body().let { body ->
            val result = ApiResult.Success(LatestRate.from(body!!))
            val favorites = interactorDatabase.getFavorites()
            if (favorites is ApiResult.Success) {
                result.data?.rates?.map { valute ->
                    valute.isFavorite = favorites.data!!.find { item -> item.name.equals(valute.name) } != null
                    if (valute.isFavorite)
                        interactorDatabase.updateRateFavorite(Favorites.from(valute))
                }
            }
            return result
        }
    }

}