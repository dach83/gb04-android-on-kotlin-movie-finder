package com.example.gb04_android_on_kotlin_movie_finder.data.api.mapper

interface ApiMapper <E, D> {
    fun mapToDomain(apiEntity: E): D
}