package com.example.gb04_android_on_kotlin_movie_finder.data.api

import com.example.gb04_android_on_kotlin_movie_finder.data.api.model.movie.ApiMovieDetails
import com.example.gb04_android_on_kotlin_movie_finder.data.api.model.movie.ApiMovieList
import com.example.gb04_android_on_kotlin_movie_finder.data.api.model.tvshow.ApiTvShowList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/{path}")
    suspend fun getMovieList(
        @Path("path") path: String,
        @Query("page") page: Int
    ): ApiMovieList

    @GET("tv/{path}")
    suspend fun getTvShowList(
        @Path("path") path: String,
        @Query("page") page: Int
    ): ApiTvShowList

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): ApiMovieDetails

}