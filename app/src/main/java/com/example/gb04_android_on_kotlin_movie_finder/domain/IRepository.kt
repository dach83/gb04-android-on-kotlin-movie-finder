package com.example.gb04_android_on_kotlin_movie_finder.domain

import androidx.paging.PagingData
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.Compilation
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.ContentType
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.details.Details
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster.Poster
import kotlinx.coroutines.flow.Flow

interface IRepository {
    suspend fun requestDetails(id: Int, contentType: ContentType): Details
    fun requestCompilation(compilation: Compilation): Flow<PagingData<Poster>>
}