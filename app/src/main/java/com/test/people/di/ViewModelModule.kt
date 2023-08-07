package com.test.people.di

import androidx.lifecycle.ViewModel
import com.test.people.interactor.InteractorDatabase
import com.test.people.interactor.InteractorEntity
import com.test.people.interactor.InteractorSort
import com.test.people.ui.favorites.FavoritesViewModel
import com.test.people.ui.popular.PopularViewModel
import com.test.people.ui.sort.SortViewModel
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

    @IntoMap
    @ViewModelKey(SortViewModel::class)
    @Provides
    fun provideSortViewModel(interactorSort: InteractorSort): ViewModel {
        return SortViewModel(interactorSort)
    }

}

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ViewModelKey(val value: KClass<out ViewModel>)
