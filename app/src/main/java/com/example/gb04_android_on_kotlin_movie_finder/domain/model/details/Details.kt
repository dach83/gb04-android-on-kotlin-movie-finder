package com.example.gb04_android_on_kotlin_movie_finder.domain.model.details

data class Details(
    val id: Int,
    val title: String,
    val tagline: String,
    val overview: String,
    val posterUrl: String,
    val backdropUrl: String
)