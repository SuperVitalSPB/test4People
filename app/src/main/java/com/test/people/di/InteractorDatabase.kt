package com.test.people.di

import com.test.people.db.Favorites
import com.test.people.model.Valute
import javax.inject.Inject

class InteractorDatabase @Inject constructor(private val databaseHelper: DatabaseHelper) {

    suspend fun getFavorites(): List<Favorites> {
        return databaseHelper.database.favoritesDao().getAll() ?: emptyList()
    }

    suspend fun deleteFavorite(favorites: Favorites) =
        databaseHelper.database.favoritesDao().delete(favorites)

    suspend fun insertFavorite(favorites: Favorites) =
        databaseHelper.database.favoritesDao().insert(favorites)

}