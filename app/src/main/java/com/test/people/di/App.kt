package com.test.people.di

import android.app.Application
import android.content.pm.PackageManager

@Suppress("DEPRECATION")
class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()

    }

    companion object {
        const val EMPTY_STRING = ""
    }

}