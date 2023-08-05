package com.test.people.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.people.ui.favorites.FavoritesViewModel
import com.test.people.ui.popular.PopularViewModel
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
class ViewModelModule {

    @IntoMap
    @ViewModelKey(PopularViewModel::class)
    @Provides
    fun providePopularViewModel(interactorEntity: InteractorEntity): ViewModel {
        return PopularViewModel(interactorEntity)
    }

    @IntoMap
    @ViewModelKey(FavoritesViewModel::class)
    @Provides
    fun provideFavoritesViewModel(interactorDatabase: InteractorDatabase): ViewModel {
        return FavoritesViewModel(interactorDatabase)
    }

}

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)
