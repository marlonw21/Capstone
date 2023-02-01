package com.mwdevs.capstone.coins.data.remote

import com.mwdevs.capstone.coins.data.remote.model.BookDetailsResponse
import com.mwdevs.capstone.coins.data.remote.model.Payload
import com.mwdevs.capstone.coins.data.remote.model.ResponseModel
import com.mwdevs.capstone.coins.data.remote.model.TickerResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface BitsoServices {
    @GET("available_books/")
    suspend fun getAvailableBooks(): ResponseModel<List<Payload>?>

    @GET("order_book/")
    suspend fun getBookDetail(
        @Query("book") book: String
    ): ResponseModel<BookDetailsResponse?>

    @GET("ticker/")
    suspend fun getTicker(
        @Query("book") book: String
    ): ResponseModel<TickerResponse?>
}