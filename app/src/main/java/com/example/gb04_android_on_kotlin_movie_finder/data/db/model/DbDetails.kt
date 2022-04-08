package com.example.gb04_android_on_kotlin_movie_finder.data.db.model

import androidx.room.Entity
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.ContentType
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.details.Details
import java.util.*


@Entity(
    tableName = "details",
    primaryKeys = ["contentId", "contentType"]
)
data class DbDetails(
    val contentId: Int,
    val contentType: ContentType,
    val userReview: String,
    val favourites: Boolean,
    val storeTime: Long = Calendar.getInstance().timeInMillis
) {
    fun isEmpty() = userReview.isEmpty() && !favourites

    companion object {
        fun fromDomain(details: Details) =
            DbDetails(
                details.contentId,
                details.contentType,
                details.userReview,
                details.favorites
            )
    }
}
