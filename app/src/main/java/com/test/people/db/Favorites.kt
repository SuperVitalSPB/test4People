package com.test.people.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.people.model.Valute

@Entity
data class Favorites constructor(@PrimaryKey var name: String,
                                             val rate: Double) {
    companion object {
        fun from(valute: Valute): Favorites = Favorites(valute.name, valute.rate)
    }
}