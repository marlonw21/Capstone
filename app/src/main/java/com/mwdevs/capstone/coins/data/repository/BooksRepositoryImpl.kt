package com.mwdevs.capstone.coins.data.repository

import com.mwdevs.capstone.coins.data.remote.BitsoServices
import com.mwdevs.capstone.coins.data.remote.model.BookDetailsResponse
import com.mwdevs.capstone.coins.data.remote.model.Payload
import com.mwdevs.capstone.coins.data.remote.model.TickerResponse
import com.mwdevs.capstone.coins.domain.repository.BooksRepository
import com.mwdevs.capstone.utils.retrofit.RetrofitInstance
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler

class BooksRepositoryImpl: RetrofitInstance<BitsoServices>(), BooksRepository {
    override var api: BitsoServices = client.create(BitsoServices::class.java)

    override suspend fun getBooks(): ResponseHandler<List<Payload>?> = callService { api.getAvailableBooks() }

    override suspend fun getTicker(book: String): ResponseHandler<TickerResponse?> = callService { api.getTicker(book) }

    override suspend fun getBookDetail(book: String): ResponseHandler<BookDetailsResponse?> = callService { api.getBookDetail(book) }

}