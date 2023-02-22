package com.mwdevs.capstone.coins.di

import com.mwdevs.capstone.coins.data.remote.BitsoServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.bitso.com/v3/"

    @Singleton
    @Provides
    fun provideBitsoApi(loggingInterceptor: HttpLoggingInterceptor): BitsoServices {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        chain.proceed(
                            chain.request()
                                .newBuilder()
                                .header("User-Agent", "okhttp:4.9.0")
                                .build()
                        )
                    }
                    .addInterceptor(loggingInterceptor)
                    .build()
            )
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor{
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }


}