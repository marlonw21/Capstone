package com.mwdevs.capstone.coins.data.remote

import com.mwdevs.capstone.coins.data.remote.model.BookDTO
import com.mwdevs.capstone.coins.data.remote.model.BookDetailsResponseDTO
import com.mwdevs.capstone.coins.data.remote.model.ResponseModel
import com.mwdevs.capstone.coins.data.remote.model.TickerResponseDTO
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface BitsoServices {
    @GET("available_books/")
    suspend fun getAvailableBooks(): ResponseModel<List<BookDTO>>

    @GET("order_book/")
    suspend fun getBookDetail(
        @Query("book") book: String
    ): ResponseModel<BookDetailsResponseDTO>

    @GET("ticker/")
    fun getTicker(
        @Query("book") book: String
    ): Observable<ResponseModel<TickerResponseDTO>>
}
