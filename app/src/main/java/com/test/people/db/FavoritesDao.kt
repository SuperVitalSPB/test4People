package com.test.people.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FavoritesDao {

    @Query("SELECT * FROM favorites")
    fun getAll(): List<Favorites?>?

    @Query("SELECT * FROM favorites WHERE name = :name")
    fun getByName(name: String): Favorites?

    @Insert
    fun insert(name: String)

    @Delete
    fun delete(name: String)
}

