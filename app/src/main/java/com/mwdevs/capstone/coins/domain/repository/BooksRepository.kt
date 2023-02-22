package com.mwdevs.capstone.coins.domain.repository

import com.mwdevs.capstone.coins.data.local.BooksEntity
import com.mwdevs.capstone.coins.data.remote.model.BookDetailsResponseDTO
import com.mwdevs.capstone.coins.data.remote.model.TickerResponseDTO
import com.mwdevs.capstone.utils.models.ResponseHandler

interface BooksRepository {
    suspend fun getBooks(): ResponseHandler<List<BooksEntity>>
    suspend fun getTicker(book: String): ResponseHandler<TickerResponseDTO>
    suspend fun getBookDetail(book: String): ResponseHandler<BookDetailsResponseDTO>
}