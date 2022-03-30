package com.example.gb04_android_on_kotlin_movie_finder.di

import com.example.gb04_android_on_kotlin_movie_finder.data.Repository
import com.example.gb04_android_on_kotlin_movie_finder.domain.IRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMovieRepository(repository: Repository): IRepository
}