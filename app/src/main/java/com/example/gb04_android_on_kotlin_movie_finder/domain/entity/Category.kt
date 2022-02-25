package com.example.gb04_android_on_kotlin_movie_finder.domain.entity

enum class Category(val title: String) {
    UPCOMING("Upcoming"),
    NOW_PLAYING("Now playing"),
    POPULAR("Popular"),
    TOP_RATED("Top rated"),
}

val movieCategories = listOf<Category>(
    Category.UPCOMING,
    Category.NOW_PLAYING,
    Category.POPULAR,
    Category.TOP_RATED
)