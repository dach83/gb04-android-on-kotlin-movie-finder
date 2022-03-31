package com.example.gb04_android_on_kotlin_movie_finder.domain.model.details

import com.example.gb04_android_on_kotlin_movie_finder.domain.model.image.Image

data class Details(
    val id: Int,
    val title: String,
    val tagline: String,
    val overview: String,
    val posterImage: Image,
    val backdropImage: Image
)