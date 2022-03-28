package com.example.gb04_android_on_kotlin_movie_finder.data.api

import com.example.gb04_android_on_kotlin_movie_finder.data.api.model.movie.ApiMovieDetails
import com.example.gb04_android_on_kotlin_movie_finder.data.api.model.movie.ApiMovieList
import com.example.gb04_android_on_kotlin_movie_finder.data.api.model.tvshow.ApiTvShowList
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

    @GET("tv/airing_today")
    suspend fun getAiringTodayTvShows(
        @Query("page") page: Int
    ): ApiTvShowList

    @GET("tv/on_the_air")
    suspend fun getCurrentlyAiringTvShows(
        @Query("page") page: Int
    ): ApiTvShowList

    @GET("tv/popular")
    suspend fun getPopularTvShows(
        @Query("page") page: Int
    ): ApiTvShowList

    @GET("tv/top_rated")
    suspend fun getTopRatedTvShows(
        @Query("page") page: Int
    ): ApiTvShowList

}