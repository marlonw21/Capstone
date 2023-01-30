package com.mwdevs.capstone.utils.retrofit.models

import com.mwdevs.capstone.coins.data.remote.model.ResponseModel

sealed class ResponseHandler<T>(val data: ResponseModel<T?>?=null, val errorBody: ErrorResponse?= null){
    class Success<T>(data: ResponseModel<T?>): ResponseHandler<T>(data)
    class Error<T>(data: ResponseModel<T?>?=null, errorBody: ErrorResponse?): ResponseHandler<T>(data, errorBody)
}
