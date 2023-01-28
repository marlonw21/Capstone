package com.mwdevs.capstone.utils.retrofit.models

sealed class ResponseHandler<T>(val data: T?= null, errorBody: ApiErrorResponse?= null){
    class Success<T>(data: T?): ResponseHandler<T>(data)
    class Error<T>(data: T?= null, errorBody: ApiErrorResponse?): ResponseHandler<T>(data, errorBody)
}
