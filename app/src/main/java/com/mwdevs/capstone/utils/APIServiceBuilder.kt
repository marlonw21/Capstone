package com.mwdevs.capstone.utils

import com.mwdevs.capstone.coins.data.remote.BitsoServices
import com.mwdevs.capstone.coins.data.remote.model.AvailableBooksResponse
import com.mwdevs.capstone.coins.data.remote.model.ResponseModel
import com.mwdevs.capstone.utils.retrofit.RetrofitInstance
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler

class APIServiceBuilder: RetrofitInstance<BitsoServices>() {
    suspend fun getTest(): ResponseHandler<AvailableBooksResponse?> = callService {
        api.getAvailableBooks()
    }
    override var api: BitsoServices = client.create(BitsoServices::class.java)

}