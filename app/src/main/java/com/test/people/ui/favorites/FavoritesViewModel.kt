package com.test.people.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.people.api.ApiResult
import com.test.people.db.Favorites
import com.test.people.di.App.Companion.EMPTY_STRING
import com.test.people.interactor.InteractorDatabase
import com.test.people.model.Valute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel(val interactorDatabase: InteractorDatabase) : ViewModel() {

    private val _favRates = MutableStateFlow(ApiResult.startFavorites())
    val favRates: StateFlow<ApiResult<List<Favorites>>> = _favRates

    var noDataString = EMPTY_STRING

    fun loadFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = interactorDatabase.getFavorites()
            if (result is ApiResult.Error && result.errorMessage?.equals(EMPTY_STRING) ?: true)
                result.errorMessage = noDataString
            _favRates.value = result
        }
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
        loadFavorites()
    }

}