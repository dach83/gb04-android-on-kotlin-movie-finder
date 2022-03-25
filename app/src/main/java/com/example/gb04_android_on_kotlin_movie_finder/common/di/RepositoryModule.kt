package com.example.gb04_android_on_kotlin_movie_finder.common.di

import com.example.gb04_android_on_kotlin_movie_finder.common.data.MovieRepository
import com.example.gb04_android_on_kotlin_movie_finder.common.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMovieRepository(repository: MovieRepository): IMovieRepository
}