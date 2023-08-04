package com.test.people.di

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @Provides
    fun getDatabaseHelper() = DatabaseHelper()
}