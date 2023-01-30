package com.mwdevs.capstone.utils

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

fun <T>String.fromJsonToClass(toClass: Class<T>): T? = try {
    Gson().fromJson(this, toClass)
}catch (e: Exception){
    when(e){
        is JsonSyntaxException -> null
        else -> null
    }
}