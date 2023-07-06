package com.mwdevs.capstone.coins.di

import com.mwdevs.capstone.coins.data.repository.BooksRepositoryImpl
import com.mwdevs.capstone.coins.domain.repository.BooksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun binsBookRepository(
        repositoryImpl: BooksRepositoryImpl
    ): BooksRepository
}
