package com.example.gb04_android_on_kotlin_movie_finder.data.api

import com.example.gb04_android_on_kotlin_movie_finder.domain.model.Compilation

object ApiConstants {

    const val SERVICE_BASE_URL = "https://api.themoviedb.org/3/"
    private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500"

    fun compilationPath(compilation: Compilation) = when (compilation) {
        Compilation.UPCOMING_MOVIES -> "upcoming"
        Compilation.NOW_PLAYING_MOVIES -> "now_playing"
        Compilation.POPULAR_MOVIES -> "popular"
        Compilation.TOP_RATED_MOVIES -> "top_rated"
        Compilation.AIRING_TODAY_TVSHOWS -> "airing_today"
        Compilation.CURRENTLY_AIRING_TV_SHOWS -> "on_the_air"
        Compilation.POPULAR_TVSHOWS -> "popular"
        Compilation.TOP_RATED_TVSHOWS -> "top_rated"
    }

    fun imageUrl(path: String?): String =
        if (path.isNullOrEmpty()) "" else IMAGE_BASE_URL + path

}