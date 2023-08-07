package com.test.people.model

import com.test.people.db.Favorites

data class Valute (
    val name: String,
    val rate: Double,
    var isFavorite: Boolean,
    ) {

    companion object {
        fun from(favorites: Favorites): Valute = Valute(favorites.name, favorites.rate, true)
    }
}