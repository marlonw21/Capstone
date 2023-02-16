package com.mwdevs.capstone.coins.di

import android.app.Application
import androidx.room.Room
import com.mwdevs.capstone.coins.data.local.BooksDatabase
import com.mwdevs.capstone.coins.data.remote.BitsoServices
import com.mwdevs.capstone.coins.data.repository.BooksRepositoryImpl
import com.mwdevs.capstone.coins.domain.repository.BooksRepository
import com.mwdevs.capstone.coins.domain.use_case.GetBookDetailUseCase
import com.mwdevs.capstone.coins.domain.use_case.GetBookListUseCase
import com.mwdevs.capstone.coins.domain.use_case.GetTickerUseCase
import com.mwdevs.capstone.utils.retrofit.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoinsModule {

    @Singleton
    @Provides
    fun provideBitsoApi(): BitsoServices{
        return Retrofit.Builder()
            .baseUrl(RetrofitInstance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                .addInterceptor(RetrofitInstance.getInterceptor())
                .build())
            .build()
            .create()
    }

    @Singleton
    @Provides
    fun provideBooksDatabase(app: Application): BooksDatabase =
        Room.databaseBuilder(
            app,
            BooksDatabase::class.java,
            "books.db"
        ).build()


    @Provides
    @Singleton
    fun provideBooksRepository(api: BitsoServices, db: BooksDatabase): BooksRepository = BooksRepositoryImpl(api, db)

    @Provides
    @Singleton
    fun provideGetBookListUseCase(repository: BooksRepository): GetBookListUseCase = GetBookListUseCase(repository)

    @Singleton
    @Provides
    fun provideGetTickerUseCase(repository: BooksRepository): GetTickerUseCase = GetTickerUseCase(repository)

    @Singleton
    @Provides
    fun provideGetBookDetailUseCase(repository: BooksRepository): GetBookDetailUseCase = GetBookDetailUseCase(repository)




}