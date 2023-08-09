package com.test.people.di

import com.test.people.interactor.InteractorDatabase
import com.test.people.interactor.InteractorEntity
import com.test.people.interactor.InteractorSort
import com.test.people.repository.RepositoryDatabase
import com.test.people.repository.RepositoryService
import dagger.Component

@Component (modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

    fun getNetworkUtils(): NetworkUtils

    fun getInteractorEntity(): InteractorEntity

    fun getViewModelFactory(): ViewModelFactory

    fun getDatabaseHelper(): DatabaseHelper

    fun getInteractorDatabase(): InteractorDatabase

    fun getInteractorSort(): InteractorSort

    fun getRepositoryDatabase(): RepositoryDatabase

    fun getRepositoryService(): RepositoryService
}