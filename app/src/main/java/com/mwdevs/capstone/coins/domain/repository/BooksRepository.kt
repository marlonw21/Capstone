package com.mwdevs.capstone.coins.domain.repository

import com.mwdevs.capstone.coins.data.remote.model.BookDetailsResponse
import com.mwdevs.capstone.coins.data.remote.model.Payload
import com.mwdevs.capstone.coins.data.remote.model.TickerResponse
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler

interface BooksRepository {
    suspend fun getBooks(): ResponseHandler<List<Payload>?>
    suspend fun getTicker(book: String): ResponseHandler<TickerResponse?>
    suspend fun getBookDetail(book: String): ResponseHandler<BookDetailsResponse?>
}