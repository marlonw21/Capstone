package com.mwdevs.capstone.coins.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [BooksEntity::class],
    version = 1
)
abstract class BooksDatabase : RoomDatabase() {

    abstract val booksDao: BooksDao
}
