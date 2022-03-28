package com.example.gb04_android_on_kotlin_movie_finder.domain.model.compilation

import com.example.gb04_android_on_kotlin_movie_finder.R

sealed class Compilation(val title: Int) {

    sealed class MoviesCompilation(title: Int) : Compilation(title) {
        object UpcomingMovies : MoviesCompilation(R.string.upcoming_label)
        object NowPlayingMovies : MoviesCompilation(R.string.now_playing_label)
        object PopularMovies : MoviesCompilation(R.string.popular_label)
        object TopRatedMovies : MoviesCompilation(R.string.top_rated_label)
    }

    sealed class TvShowsCompilation(title: Int) : Compilation(title) {
        object AiringTodayTvShows : TvShowsCompilation(R.string.airing_today_label)
        object CurrentlyAiringTvShows : TvShowsCompilation(R.string.currently_airing_label)
        object PopularTvShows : TvShowsCompilation(R.string.popular_label)
        object TopRatedTvShows : TvShowsCompilation(R.string.top_rated_label)
    }
}

val moviesCompilation: List<Compilation> = Compilation.MoviesCompilation::class.nestedClasses.map {
    it.objectInstance as Compilation
}

val tvShowsCompilation: List<Compilation> = Compilation.TvShowsCompilation::class.nestedClasses.map {
    it.objectInstance as Compilation
}