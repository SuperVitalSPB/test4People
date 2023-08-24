package com.test.people.repository

import com.test.people.db.Favorites
import com.test.people.di.DatabaseHelper
import javax.inject.Inject

class RepositoryDatabase @Inject constructor(private val databaseHelper: DatabaseHelper) {

    fun getAll(): List<Favorites> = databaseHelper.database.favoritesDao().getAll()

    fun deleteFavorite(favorites: Favorites) =
        databaseHelper.database.favoritesDao().delete(favorites)

    fun insertFavorite(favorites: Favorites) =
        databaseHelper.database.favoritesDao().insert(favorites)

    fun updateRateFavorite(favorites: Favorites) =
        databaseHelper.database.favoritesDao().update(favorites)

}