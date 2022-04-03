package com.example.gb04_android_on_kotlin_movie_finder.domain.model.poster

import com.example.gb04_android_on_kotlin_movie_finder.domain.model.ContentType
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.image.Image

data class Poster(
    val contentId: Int,
    val contentType: ContentType,
    val title: String,
    val adult: Boolean,
    val posterImage: Image
)