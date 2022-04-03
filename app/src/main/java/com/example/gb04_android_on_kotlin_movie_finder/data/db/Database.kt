package com.example.gb04_android_on_kotlin_movie_finder.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gb04_android_on_kotlin_movie_finder.data.db.daos.DetailsDao
import com.example.gb04_android_on_kotlin_movie_finder.data.db.model.DbDetails

@Database(entities = [DbDetails::class], version = 2, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract fun detailsDao(): DetailsDao
}