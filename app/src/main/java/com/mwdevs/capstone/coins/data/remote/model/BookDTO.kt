package com.mwdevs.capstone.coins.data.remote.model

import com.mwdevs.capstone.coins.data.local.BooksEntity

data class BookDTO(
    val book: String,
    var coinName: String,
    val maximum_amount: String,
    val maximum_price: String,
    val maximum_value: String,
    val minimum_amount: String,
    val minimum_price: String,
    val minimum_value: String
)