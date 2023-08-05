package com.test.people.di

import android.content.Context
import dagger.Component

@Component (modules = [AppModule::class, StorageModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {

    fun getNetworkUtils(): NetworkUtils

    fun getInteractorEntity(): InteractorEntity

    fun getViewModelFactory(): ViewModelFactory

    fun injectApp(app: App)
}