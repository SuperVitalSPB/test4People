package com.test.people.interactor

import com.test.people.api.ApiResult
import com.test.people.db.Favorites
import com.test.people.di.App.Companion.EMPTY_STRING
import com.test.people.repository.RepositoryDatabase
import javax.inject.Inject

class InteractorDatabase @Inject constructor(private val repositoryDatabase: RepositoryDatabase) {

    fun getFavorites(): ApiResult<List<Favorites>> {
        val favorites = repositoryDatabase.getAll()
        if (favorites.size == 0 )
            return ApiResult.Error(EMPTY_STRING)
        else
            return ApiResult.Success(favorites)
    }

    fun deleteFavorite(favorites: Favorites) =
        repositoryDatabase.deleteFavorite(favorites)

    fun insertFavorite(favorites: Favorites) =
        repositoryDatabase.insertFavorite(favorites)

    fun updateRateFavorite(favorites: Favorites) =
        repositoryDatabase.updateRateFavorite(favorites)
    
}