package com.mwdevs.capstone.coins.domain.delegate

import com.mwdevs.capstone.coins.domain.model.CoinUIModel
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler

interface BookListDelegate<T>{
    suspend fun toUiModel(apiResponse: T?)
}