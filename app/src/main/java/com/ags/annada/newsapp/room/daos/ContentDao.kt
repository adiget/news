package com.ags.annada.newsapp.room.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ags.annada.newsapp.room.entities.Content

@Dao
interface ContentDao {
    @get:Query("SELECT * from content_table")
    val all: LiveData<List<Content>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg content: Content)

    @Query("DELETE from content_table")
    suspend fun deleteAll()
}