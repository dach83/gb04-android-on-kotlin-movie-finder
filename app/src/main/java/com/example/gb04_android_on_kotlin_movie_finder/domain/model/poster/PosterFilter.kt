package com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster

import android.os.Parcelable
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.Compilation
import kotlinx.parcelize.Parcelize

sealed class PosterFilter : Parcelable {
    @Parcelize
    data class CompilationFilter(val compilation: Compilation) : PosterFilter()

    @Parcelize
    object FavoritesFilter : PosterFilter()
}