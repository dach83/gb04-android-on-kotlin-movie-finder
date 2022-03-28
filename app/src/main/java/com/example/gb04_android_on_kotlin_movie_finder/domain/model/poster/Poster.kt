package com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster

sealed class Poster(val posterPath: String, val title: String) {
    class MoviePoster(val movieId: Int, moviePosterPath: String, movieTitle: String) :
        Poster(moviePosterPath, movieTitle)

    class TvShowPoster(val tvShowId: Int, tvShowPosterPath: String, tvShowTitle: String) :
        Poster(tvShowPosterPath, tvShowTitle)
}