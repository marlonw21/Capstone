package com.mwdevs.capstone.coins.domain.repository

import com.mwdevs.capstone.coins.data.remote.model.BookDetailsResponseDTO
import com.mwdevs.capstone.coins.data.remote.model.BookDTO
import com.mwdevs.capstone.coins.data.remote.model.TickerResponseDTO
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler

interface BooksRepository {
    suspend fun getBooks(): ResponseHandler<List<BookDTO>>
    suspend fun getTicker(book: String): ResponseHandler<TickerResponseDTO>
    suspend fun getBookDetail(book: String): ResponseHandler<BookDetailsResponseDTO>
}