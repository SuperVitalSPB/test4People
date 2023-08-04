package com.test.people.di

import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun getNetworkUtils() = NetworkUtils()
}