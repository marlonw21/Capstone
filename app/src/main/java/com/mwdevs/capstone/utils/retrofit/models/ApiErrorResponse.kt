package com.mwdevs.capstone.utils.retrofit.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Response

data class ApiErrorResponse(
    @SerializedName("Response")
    @Expose
    val response: Response<ErrorResponse>?=null
)
data class ErrorResponse(
    @SerializedName("protocol")
    @Expose
    var protocol: String? = null,
    @SerializedName("code")
    @Expose
    var code: Int? = null,
//    @SerializedName("message")
//    @Expose
//    var message: String? = null,
    @SerializedName("url")
    @Expose
    var url: String? = null,

)


