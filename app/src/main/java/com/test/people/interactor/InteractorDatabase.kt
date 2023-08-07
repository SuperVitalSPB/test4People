package com.test.people.interactor

import com.test.people.api.ApiResult
import com.test.people.db.Favorites
import com.test.people.di.App.Companion.EMPTY_STRING
import com.test.people.di.DatabaseHelper
import javax.inject.Inject

class InteractorDatabase @Inject constructor(private val databaseHelper: DatabaseHelper) {

    suspend fun getFavorites(): ApiResult<List<Favorites>> {
        val favorites = databaseHelper.database.favoritesDao().getAll()
        if (favorites.size == 0 )
            return ApiResult.Error(EMPTY_STRING)
        else
            return ApiResult.Success(databaseHelper.database.favoritesDao().getAll())
    }

    suspend fun deleteFavorite(favorites: Favorites) =
        databaseHelper.database.favoritesDao().delete(favorites)

    suspend fun insertFavorite(favorites: Favorites) =
        databaseHelper.database.favoritesDao().insert(favorites)

    suspend fun updateRateFavorite(favorites: Favorites) =
        databaseHelper.database.favoritesDao().update(favorites)


}