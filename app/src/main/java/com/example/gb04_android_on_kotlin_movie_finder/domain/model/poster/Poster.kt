package com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster

import com.example.gb04_android_on_kotlin_movie_finder.domain.model.ContentType

data class Poster(
    val id: Int,
    val contentType: ContentType,
    val posterUrl: String,
    val title: String
)