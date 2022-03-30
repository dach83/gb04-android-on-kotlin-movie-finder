package com.example.gb04_android_on_kotlin_movie_finder.domain.model

import com.example.gb04_android_on_kotlin_movie_finder.R

enum class Compilation(val contentType: ContentType, val title: Int) {
    UPCOMING_MOVIES(ContentType.MOVIE, R.string.upcoming_label),
    NOW_PLAYING_MOVIES(ContentType.MOVIE, R.string.now_playing_label),
    POPULAR_MOVIES(ContentType.MOVIE, R.string.popular_label),
    TOP_RATED_MOVIES(ContentType.MOVIE, R.string.top_rated_label),

    AIRING_TODAY_TVSHOWS(ContentType.TVSHOW, R.string.airing_today_label),
    CURRENTLY_AIRING_TV_SHOWS(ContentType.TVSHOW, R.string.currently_airing_label),
    POPULAR_TVSHOWS(ContentType.TVSHOW, R.string.popular_label),
    TOP_RATED_TVSHOWS(ContentType.TVSHOW, R.string.top_rated_label)
}

val movieCompilations = Compilation.values().filter { it.contentType == ContentType.MOVIE }
val tvShowCompilations = Compilation.values().filter { it.contentType == ContentType.TVSHOW }
