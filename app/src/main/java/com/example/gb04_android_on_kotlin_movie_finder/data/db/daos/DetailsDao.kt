package com.example.gb04_android_on_kotlin_movie_finder.data.db.daos

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.gb04_android_on_kotlin_movie_finder.data.db.model.DbDetails
import com.example.gb04_android_on_kotlin_movie_finder.domain.model.ContentType

@Dao
interface DetailsDao {

    @Query("SELECT * FROM details WHERE contentId = :contentId AND contentType = :contentType")
    suspend fun getDetails(contentId: Int, contentType: ContentType): DbDetails?

    @Insert(onConflict = REPLACE)
    suspend fun insertDetails(details: DbDetails)

    @Query("SELECT * FROM details WHERE favourites = :favorites")
    fun favoritesPagingSource(favorites: Boolean = true): PagingSource<Int, DbDetails>
}