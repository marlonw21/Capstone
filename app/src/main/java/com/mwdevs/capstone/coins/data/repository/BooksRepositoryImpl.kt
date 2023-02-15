package com.mwdevs.capstone.coins.data.repository

import com.mwdevs.capstone.coins.data.remote.BitsoServices
import com.mwdevs.capstone.coins.data.remote.model.BookDetailsResponseDTO
import com.mwdevs.capstone.coins.data.remote.model.BookDTO
import com.mwdevs.capstone.coins.data.remote.model.TickerResponseDTO
import com.mwdevs.capstone.coins.domain.repository.BooksRepository
import com.mwdevs.capstone.utils.retrofit.RetrofitInstance
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(private val api: BitsoServices): RetrofitInstance(), BooksRepository {

    override suspend fun getBooks(): ResponseHandler<List<BookDTO>> = callService { api.getAvailableBooks() }

    override suspend fun getTicker(book: String): ResponseHandler<TickerResponseDTO> = callService { api.getTicker(book) }

    override suspend fun getBookDetail(book: String): ResponseHandler<BookDetailsResponseDTO> = callService { api.getBookDetail(book) }

}