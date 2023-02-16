package com.mwdevs.capstone.coins.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BooksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooks(books: List<BooksEntity>)

    @Query("SELECT * FROM booksEntity")
    suspend fun getBooks(): List<BooksEntity>
}