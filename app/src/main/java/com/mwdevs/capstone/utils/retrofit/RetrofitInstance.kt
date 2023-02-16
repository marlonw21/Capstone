package com.mwdevs.capstone.utils.retrofit

import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.mwdevs.capstone.coins.data.local.BooksDatabase
import com.mwdevs.capstone.coins.data.remote.model.ResponseModel
import com.mwdevs.capstone.utils.retrofit.models.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.UnknownHostException

abstract class RetrofitInstance {

    companion object {
        const val BASE_URL = "https://api.bitso.com/v3/"
        fun getRetrofitInstance(): Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(OkHttpClient.Builder()
                .addInterceptor(getInterceptor())
                .build())
            .build()
        fun getInterceptor():HttpLoggingInterceptor{
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            return logging
        }
    }

    inline fun <T> callService(
        cb: () -> ResponseModel<T>
    ): ResponseHandler<T> {
        return try {
            val response =  cb.invoke()
            response.let {
                if (it.success && it.successBody != null)
                    ResponseHandler.Success(
                        it.successBody!!
                    )
                else
                    ResponseHandler.Error(
                        data = null,
                        errorMsg = response.errorBody?.message
                    )
            }
        } catch (e: Exception) {
            ResponseHandler.Error(
                errorMsg = e.message
            )
        }
        catch (e: HttpException){
            ResponseHandler.Error(
                errorMsg = e.message
            )
        }
        catch (e: JsonSyntaxException){
            ResponseHandler.Error(
                errorMsg = e.message
            )
        }
        catch (e: UnknownHostException){
            ResponseHandler.Error(
                errorMsg = e.message
            )
        }
    }
}