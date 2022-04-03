package com.example.gb04_android_on_kotlin_movie_finder.presentation.details

import com.example.gb04_android_on_kotlin_movie_finder.domain.model.details.Details
import java.text.FieldPosition

data class DetailsViewState(
    val isLoading: Boolean = true,
    val details: Details? = null,
    val tabPosition: Int = 0
)
