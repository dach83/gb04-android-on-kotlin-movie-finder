package com.example.gb04_android_on_kotlin_movie_finder.common.data.api

import com.example.gb04_android_on_kotlin_movie_finder.common.data.api.model.movie.compilation.ApiMovieCompilation
import com.example.gb04_android_on_kotlin_movie_finder.common.data.api.model.movie.detail.ApiMovieDetails
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("{movie_id}")
    suspend fun getDetails(
        @Path("movie_id") movieId: Int
    ): ApiMovieDetails

    @GET("upcoming")
    suspend fun getUpcoming(
        @Query("page") page: Int
    ): ApiMovieCompilation

    @GET("now_playing")
    suspend fun getNowPlaying(
        @Query("page") page: Int
    ): ApiMovieCompilation

    @GET("popular")
    suspend fun getPopular(
        @Query("page") page: Int
    ): ApiMovieCompilation

    @GET("top_rated")
    suspend fun getTopRated(
        @Query("page") page: Int
    ): ApiMovieCompilation

}