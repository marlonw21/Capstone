package com.mwdevs.capstone.coins.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BooksEntity(
    @PrimaryKey val id: Int? = null,
    val book: String,
    var coinName: String?,
    val maximum_amount: String,
    val maximum_price: String,
    val maximum_value: String,
    val minimum_amount: String,
    val minimum_price: String,
    val minimum_value: String
)
