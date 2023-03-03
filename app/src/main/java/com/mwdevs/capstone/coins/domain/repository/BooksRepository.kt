package com.mwdevs.capstone.coins.domain.repository

import com.mwdevs.capstone.coins.data.local.BooksEntity
import com.mwdevs.capstone.coins.data.remote.model.BookDetailsResponseDTO
import com.mwdevs.capstone.coins.data.remote.model.TickerResponseDTO
import com.mwdevs.capstone.utils.models.ResponseHandler
import io.reactivex.Observable

interface BooksRepository {
    suspend fun getBooks(): ResponseHandler<List<BooksEntity>>
    fun getTicker(book: String): Observable<ResponseHandler<TickerResponseDTO>>
    suspend fun getBookDetail(book: String): ResponseHandler<BookDetailsResponseDTO>
}
