package com.mwdevs.capstone.utils.retrofit

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mwdevs.capstone.coins.data.remote.model.Payload
import com.mwdevs.capstone.coins.data.remote.model.ResponseModel
import com.mwdevs.capstone.utils.retrofit.models.ApiErrorResponse
import com.mwdevs.capstone.utils.retrofit.models.ErrorResponse
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

abstract class RetrofitInstance<T: Any> {
    val ok = OkHttpClient()
    companion object{
        const val BASE_URL = "https://api.bitso.com/v3/"
        fun getRetrofitInstance(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(OkHttpClient.Builder().build())
            .build()
    }
    protected var client = getRetrofitInstance()
    protected abstract var api: T

    suspend inline fun <T>callService(
        crossinline cb: suspend () -> ResponseModel<T?>
    ): ResponseHandler<ResponseModel<T?>> {
        return try {
            val response = withContext(Dispatchers.IO){ cb.invoke()}
            response.let {
                if (it.success)
                    ResponseHandler.Success(it)
                else
                    ResponseHandler.Error(
                        it,
                        errorBody = null
                    )
            }
        }catch (e: Exception){
            withContext(Dispatchers.IO){
                when(e){
                    is HttpException -> {
                        val error = Gson().fromJson(e.response()!!.raw().toString().replace("Response", ""), ErrorResponse::class.java)
                        val error2 = e.response()!!.raw().toString().replace("Response", "")
                        ResponseHandler.Error(
                            errorBody = null
                        )
                    }
                    else -> ResponseHandler.Error(
                        errorBody = ApiErrorResponse().apply {
                            this.response?.body()?.message ="Unknown Error"
                        }
                    )
                }
            }
        }
    }
}