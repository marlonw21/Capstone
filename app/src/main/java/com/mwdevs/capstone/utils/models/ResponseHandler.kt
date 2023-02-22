package com.mwdevs.capstone.utils.models

sealed class ResponseHandler<T>(val data: T?=null, val errorMsg: String?= null){
    class Success<T>(data: T): ResponseHandler<T>(data)
    class Error<T>(data: T?=null, errorMsg: String?): ResponseHandler<T>(data, errorMsg)
}
