package com.example.gb04_android_on_kotlin_movie_finder.di

import android.content.Context
import androidx.room.Room
import com.example.gb04_android_on_kotlin_movie_finder.data.db.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext app: Context): Database =
        Room.databaseBuilder(app, Database::class.java, "Movies.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideDetailsDao(database: Database) = database.detailsDao()
}