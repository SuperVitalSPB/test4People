package com.test.people.interactor

import com.test.people.api.ApiResult
import com.test.people.db.Favorites
import com.test.people.di.App.Companion.EMPTY_STRING
import com.test.people.di.DatabaseHelper
import com.test.people.repository.RepositoryDatabase
import javax.inject.Inject

class InteractorDatabase @Inject constructor(private val repositoryDatabase: RepositoryDatabase) {

    suspend fun getFavorites(): ApiResult<List<Favorites>> {
        val favorites = repositoryDatabase.getAll()
        if (favorites.size == 0 )
            return ApiResult.Error(EMPTY_STRING)
        else
            return ApiResult.Success(favorites)
    }

    suspend fun deleteFavorite(favorites: Favorites) =
        repositoryDatabase.deleteFavorite(favorites)

    suspend fun insertFavorite(favorites: Favorites) =
        repositoryDatabase.insertFavorite(favorites)

    suspend fun updateRateFavorite(favorites: Favorites) =
        repositoryDatabase.updateRateFavorite(favorites)
    
}