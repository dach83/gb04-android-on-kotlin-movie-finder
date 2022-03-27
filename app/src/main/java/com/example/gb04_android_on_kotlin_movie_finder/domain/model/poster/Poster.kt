package com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster

sealed class Poster (val id: Int, val posterPath: String, val title: String) {
    class MoviePoster(movieId: Int, moviePosterPath: String, movieTitle: String): Poster(movieId, moviePosterPath, movieTitle)
    class TvShowPoster(tvShowId: Int, tvShowPosterPath: String): Poster(tvShowId, tvShowPosterPath, "")
}