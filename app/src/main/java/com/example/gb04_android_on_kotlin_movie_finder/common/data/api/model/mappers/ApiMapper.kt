package com.example.gb04_android_on_kotlin_movie_finder.common.data.api.model.mappers

interface ApiMapper <E, D> {
    fun mapToDomain(apiEntity: E): D
}