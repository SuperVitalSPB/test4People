package com.test.people.di

import com.test.people.ui.popular.PopularFragment
import com.test.people.ui.popular.PopularViewModelFactory
import dagger.Component

@Component (modules = [StorageModule::class, NetworkModule::class])
interface AppComponent {

    fun injectPopularViewmodelFactory(vm: PopularViewModelFactory)

}