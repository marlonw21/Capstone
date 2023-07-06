package com.mwdevs.capstone.coins.di

import com.mwdevs.capstone.coins.domain.repository.BooksRepository
import com.mwdevs.capstone.coins.domain.use_case.GetBookDetailUseCase
import com.mwdevs.capstone.coins.domain.use_case.GetBookListUseCase
import com.mwdevs.capstone.coins.domain.use_case.GetTickerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoinsModule {

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
