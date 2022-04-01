package com.example.gb04_android_on_kotlin_movie_finder.presentation

import androidx.paging.PagingData
import androidx.paging.filter
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.Settings
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun Flow<PagingData<Poster>>.applySettings(settings: Settings) = map { pagingData ->
    pagingData.filter { poster -> settings.enabledAdult || !poster.adult }
}

