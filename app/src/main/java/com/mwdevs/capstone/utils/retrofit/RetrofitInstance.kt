package com.mwdevs.capstone.utils.retrofit

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mwdevs.capstone.coins.data.remote.model.ResponseModel
import com.mwdevs.capstone.utils.retrofit.models.ApiErrorResponse
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

    open suspend fun <T>callService(
        cb: suspend () -> T?
    ): ResponseHandler<T?> {
        return try {
            val response = withContext(Dispatchers.IO){ cb.invoke()}
            response?.let {
                ResponseHandler.Success(it)

            } ?: ResponseHandler.Error(response, ApiErrorResponse().apply {
                this.response?.body()?.message ="Unknown Error"
            })
        }catch (e: Exception){
            withContext(Dispatchers.IO){
                Log.e("Exception", e.message.toString())
                when(e){
                    is HttpException -> {
                        val error = Gson().fromJson(e.response()!!.errorBody()!!.charStream(), ApiErrorResponse::class.java)
                        val error2 = e.response()!!.raw()
                        Log.e("HttpException", e.message.toString())
                        Log.e("HttpException", error2.toString())
                        ResponseHandler.Error(
                            errorBody = error
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

//    suspend fun <T>callApi(
//        cb: suspend () -> Response<T>
//    ): T?{
//        var responses: Response<T> = Response.error(400, ResponseBody.create(null,""))
//         withContext(Dispatchers.IO){
//             val request = async {
//                 cb.invoke()
////                     .enqueue(object : Callback<T>{
////                     override fun onResponse(call: Call<T>, response: Response<T>) {
////                         Log.e("callApi", response.body().toString())
////                         responses = response
////                     }
////
////                     override fun onFailure(call: Call<T>, t: Throwable) {
////                         Log.e("callApi", t.message ?: "error msg")
////                     }
////                 }
////
////                 )
//             }
//             request.start()
//        }
//        return responses.body()
//    }

}