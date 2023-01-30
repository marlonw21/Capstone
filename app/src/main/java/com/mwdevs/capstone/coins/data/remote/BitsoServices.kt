package com.mwdevs.capstone.coins.data.remote

import com.mwdevs.capstone.coins.data.remote.model.AvailableBooksResponse
import com.mwdevs.capstone.coins.data.remote.model.Payload
import com.mwdevs.capstone.coins.data.remote.model.ResponseModel
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler
import retrofit2.http.GET

interface BitsoServices {
    @GET("available_books/")
    suspend fun getAvailableBooks(): ResponseModel<List<Payload>?>
}