package com.mwdevs.capstone.utils

import com.mwdevs.capstone.coins.data.remote.BitsoServices
import com.mwdevs.capstone.coins.data.remote.model.BookDetailsResponse
import com.mwdevs.capstone.coins.data.remote.model.Payload
import com.mwdevs.capstone.coins.data.remote.model.TickerResponse
import com.mwdevs.capstone.utils.retrofit.RetrofitInstance
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler

class APIServiceBuilder: RetrofitInstance<BitsoServices>() {
    suspend fun getTest(): ResponseHandler<List<Payload>?> = callService { api.getAvailableBooks() }
    suspend fun getTest2(book: String): ResponseHandler<BookDetailsResponse?> = callService { api.getBookDetail(book)}
    suspend fun getTest3(book: String): ResponseHandler<TickerResponse?> = callService { api.getTicker(book)}
    override var api: BitsoServices = client.create(BitsoServices::class.java)

}