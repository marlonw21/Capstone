package com.mwdevs.capstone.utils

import com.mwdevs.capstone.coins.data.remote.BitsoServices
import com.mwdevs.capstone.coins.data.remote.model.Payload
import com.mwdevs.capstone.utils.retrofit.RetrofitInstance
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler

class APIServiceBuilder: RetrofitInstance<BitsoServices>() {
    suspend fun getTest(): ResponseHandler<List<Payload>?> = callService {
        api.getAvailableBooks()
    }
    override var api: BitsoServices = client.create(BitsoServices::class.java)

}