package com.example.gb04_android_on_kotlin_movie_finder.data.api

import com.example.gb04_android_on_kotlin_movie_finder.data.api.model.movie.ApiMovieDetails
import com.example.gb04_android_on_kotlin_movie_finder.data.api.model.movie.ApiMovieList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbApi {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): ApiMovieDetails

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int
    ): ApiMovieList

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ): ApiMovieList

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): ApiMovieList

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ): ApiMovieList

}