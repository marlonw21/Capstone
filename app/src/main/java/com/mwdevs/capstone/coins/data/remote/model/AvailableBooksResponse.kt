package com.mwdevs.capstone.coins.data.remote.model

import com.google.gson.annotations.SerializedName

data class AvailableBooksResponse(
//    val success: Boolean,
    val payload: List<Payload>,
)
data class ResponseModel<T>(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("error")
    var errorBody: ErrorBody?= null,
    @SerializedName("payload")
    var successBody: T?
)
data class ErrorBody(
    @SerializedName("code")
    val code: String?= null,
    @SerializedName("message")
    val message: String?= null
)