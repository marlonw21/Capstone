package com.mwdevs.capstone.coins.utils

import com.mwdevs.capstone.coins.data.local.BooksEntity
import com.mwdevs.capstone.coins.data.remote.model.AskDTO
import com.mwdevs.capstone.coins.data.remote.model.BidDTO
import com.mwdevs.capstone.coins.data.remote.model.BookDTO
import com.mwdevs.capstone.coins.domain.model.AskBidsModel

fun AskDTO.toAsksToUIModel(): AskBidsModel = AskBidsModel(
    amount = this.amount,
    price = this.price
)

fun BidDTO.toBidsToUIModel(): AskBidsModel = AskBidsModel(
    amount = this.amount,
    price = this.price
)

fun BookDTO.toEntityModel(): BooksEntity = BooksEntity(
    book = this.book,
    coinName = this.coinName,
    maximum_amount = this.maximum_amount,
    maximum_price = this.maximum_price,
    maximum_value = this.maximum_value,
    minimum_amount = this.minimum_amount,
    minimum_price = this.minimum_price,
    minimum_value = this.minimum_value
)