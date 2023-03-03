package com.mwdevs.capstone.coins.di

import android.app.Application
import androidx.room.Room
import com.mwdevs.capstone.coins.data.local.BooksDao
import com.mwdevs.capstone.coins.data.local.BooksDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideBooksDatabase(app: Application): BooksDatabase =
        Room.databaseBuilder(
            app,
            BooksDatabase::class.java,
            "books.db"
        ).build()

    @Singleton
    @Provides
    fun providesBooksDao(db: BooksDatabase): BooksDao = db.booksDao
}
