package com.test.people.di

import android.util.Log
import com.test.people.api.ApiResult
import com.test.people.db.Favorites
import com.test.people.model.ErrorResponse
import com.test.people.model.LatestRate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InteractorEntity @Inject constructor(private val networkUtils: NetworkUtils,
                                           private val databaseHelper: DatabaseHelper) {

    suspend fun getLatest(): ApiResult<LatestRate> {
        val result = networkUtils.retrofit.getLatest()
        result.errorBody()?.string()?.let {
            return ApiResult.Error(ErrorResponse().from(it)?.message ?: "Not response")
        }
        result.body().let { body ->
            val result = ApiResult.Success(LatestRate.from(body!!))
            val favorites = databaseHelper.database.favoritesDao().getAll() ?: emptyList()
/*
            Log.d("InteractorEntity", favorites.toString())
            databaseHelper.database.favoritesDao().let {
                it.insert(Favorites("AED", 12.3))
                it.insert(Favorites("AMD", 12.3))
                it.insert(Favorites("AOA", 12.3))
            }
            favorites = databaseHelper.database.favoritesDao().getAll() ?: emptyList()
            Log.d("InteractorEntity", favorites.toString())
*/
            result.data?.rates?.map { valute ->
                valute.isFavorite = favorites.find { item -> item.name.equals(valute.name)} != null
                if (valute.isFavorite)
                    databaseHelper.database.favoritesDao().update(Favorites.from(valute))
            }
            return result
        }
    }

}