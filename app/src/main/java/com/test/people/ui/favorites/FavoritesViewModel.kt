package com.test.people.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.people.db.Favorites
import com.test.people.di.InteractorDatabase
import com.test.people.model.Valute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesViewModel(val interactorDatabase: InteractorDatabase) : ViewModel() {

    fun getFavorites(): List<Favorites> {
        lateinit var result: List<Favorites>
        viewModelScope.launch(Dispatchers.IO) {
            result = interactorDatabase.getFavorites()
        }
        return result
    }

    fun deleteFavorite(valute: Valute) {
        viewModelScope.launch(Dispatchers.IO) {
            interactorDatabase.deleteFavorite(Favorites.from(valute))
        }
    }

    fun insertFavorite(valute: Valute) {
        viewModelScope.launch(Dispatchers.IO) {
            interactorDatabase.insertFavorite(Favorites.from(valute))
        }
    }

    fun changeFavorite(valute: Valute) {
        if (valute.isFavorite)
            insertFavorite(valute)
        else
            deleteFavorite(valute)
    }

}