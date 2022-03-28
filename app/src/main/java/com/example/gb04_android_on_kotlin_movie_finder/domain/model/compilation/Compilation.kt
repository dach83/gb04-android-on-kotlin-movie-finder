package com.example.gb04_android_on_kotlin_movie_finder.domain.model.compilation

import android.os.Parcelable
import com.example.gb04_android_on_kotlin_movie_finder.R
import kotlinx.parcelize.Parcelize

sealed class Compilation(val title: Int) : Parcelable {

    sealed class MoviesCompilation(title: Int) : Compilation(title) {
        @Parcelize
        object UpcomingMovies : MoviesCompilation(R.string.upcoming_label)

        @Parcelize
        object NowPlayingMovies : MoviesCompilation(R.string.now_playing_label)

        @Parcelize
        object PopularMovies : MoviesCompilation(R.string.popular_label)

        @Parcelize
        object TopRatedMovies : MoviesCompilation(R.string.top_rated_label)
    }

    sealed class TvShowsCompilation(title: Int) : Compilation(title) {
        @Parcelize
        object AiringTodayTvShows : TvShowsCompilation(R.string.airing_today_label)

        @Parcelize
        object CurrentlyAiringTvShows : TvShowsCompilation(R.string.currently_airing_label)

        @Parcelize
        object PopularTvShows : TvShowsCompilation(R.string.popular_label)

        @Parcelize
        object TopRatedTvShows : TvShowsCompilation(R.string.top_rated_label)
    }
}

val moviesCompilation: List<Compilation> = Compilation.MoviesCompilation::class.nestedClasses.map {
    it.objectInstance as Compilation
}

val tvShowsCompilation: List<Compilation> =
    Compilation.TvShowsCompilation::class.nestedClasses.map {
        it.objectInstance as Compilation
    }