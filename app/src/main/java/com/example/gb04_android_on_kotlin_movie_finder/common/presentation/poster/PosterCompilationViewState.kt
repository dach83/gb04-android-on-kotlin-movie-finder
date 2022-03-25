package com.example.gb04_android_on_kotlin_movie_finder.common.presentation.poster

import com.example.gb04_android_on_kotlin_movie_finder.common.presentation.Event

data class PosterCompilationViewState(
    val isLoading: Boolean = true,
    val posters: List<UiPoster> = emptyList(),
    val failure: Event<Throwable>? = null
)
