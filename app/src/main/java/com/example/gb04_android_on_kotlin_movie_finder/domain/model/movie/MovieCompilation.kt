package com.example.gb04_android_on_kotlin_movie_finder.domain.model.movie

import com.example.gb04_android_on_kotlin_movie_finder.R

enum class MovieCompilation(val title: Int) {
    UPCOMING(R.string.upcoming_label),
    NOW_PLAYING(R.string.now_playing_label),
    POPULAR(R.string.popular_label),
    TOP_RATED(R.string.top_rated_label),
}

