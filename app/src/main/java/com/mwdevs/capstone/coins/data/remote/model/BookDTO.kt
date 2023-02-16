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
){
    fun toEntityModel(): BooksEntity = BooksEntity(
        book = book,
        coinName = coinName,
        maximum_amount = maximum_amount,
        maximum_price = maximum_price,
        maximum_value = maximum_value,
        minimum_amount = minimum_amount,
        minimum_price = minimum_price,
        minimum_value = minimum_value
    )
}