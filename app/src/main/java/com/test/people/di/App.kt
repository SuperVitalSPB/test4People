package com.test.people.di

import android.app.Application
import com.test.people.api.ApiService
import com.test.people.api.RetrofitClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    val appComponent = DaggerAppComponent.create()

    override fun onCreate() {
        super.onCreate()

    }

}