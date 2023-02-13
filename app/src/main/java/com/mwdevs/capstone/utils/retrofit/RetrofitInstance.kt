package com.mwdevs.capstone.utils.retrofit

import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.mwdevs.capstone.coins.data.remote.model.ResponseModel
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitInstance<T : Any> {

    companion object {
        private const val BASE_URL = "https://api.bitso.com/v3/"
        fun getRetrofitInstance(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(OkHttpClient.Builder()
                .addInterceptor(getInterceptor())
                .build())
            .build()
        private fun getInterceptor():HttpLoggingInterceptor{
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            return logging
        }
    }

    protected var client = getRetrofitInstance()
    protected abstract var api: T

    suspend inline fun <T> callService(
        crossinline cb: suspend () -> ResponseModel<T>
    ): ResponseHandler<T> {
        return try {
            val response = withContext(Dispatchers.IO) { cb.invoke() }
            response.let {
                if (it.success)
                    ResponseHandler.Success(it)
                else
                    ResponseHandler.Error(
                        it,
                        errorBody = null
                    )
            }
        } catch (e: Exception) {
            ResponseHandler.Error(
                errorBody = e.message
            )
        }
        catch (e: HttpException){
            ResponseHandler.Error(
                errorBody = e.message
            )
        }
        catch (e: JsonSyntaxException){
            ResponseHandler.Error(
                errorBody = e.message
            )
        }
    }
}