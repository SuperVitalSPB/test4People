package com.test.people.di

import android.content.Context
import androidx.room.Room
import com.test.people.db.AppDatabase
import javax.inject.Inject


class DatabaseHelper @Inject constructor(val context: Context) {

    val database: AppDatabase by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, "database.db")
            .build()
    }

}