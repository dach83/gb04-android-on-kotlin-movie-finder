package com.example.gb04_android_on_kotlin_movie_finder.data.api

import com.example.gb04_android_on_kotlin_movie_finder.domain.model.Compilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.image.ImageSize

object ApiConstants {

    const val SERVICE_BASE_URL = "https://api.themoviedb.org/3/"

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

}