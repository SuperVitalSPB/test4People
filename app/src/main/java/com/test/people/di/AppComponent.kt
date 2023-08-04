package com.test.people.di

import com.test.people.ui.popular.PopularFragment
import dagger.Component

@Component (modules = [StorageModule::class, NetworkModule::class])
interface AppComponent {

    fun injectFragment(fragment: PopularFragment)

}