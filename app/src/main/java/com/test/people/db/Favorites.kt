package com.test.people.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Favorites {
    @PrimaryKey
    var name: String? = null
}