package com.example.gb04_android_on_kotlin_movie_finder.presentation.compilation

import com.example.gb04_android_on_kotlin_movie_finder.presentation.util.Event

data class CompilationViewState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val failure: Event<Throwable>? = null,
)