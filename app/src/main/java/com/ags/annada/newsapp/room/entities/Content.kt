package com.ags.annada.newsapp.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "content_table")
data class Content  (
    @PrimaryKey @ColumnInfo(name = "contentId") val contentId: String,
    @ColumnInfo(name = "contentSourceLogo") val contentSourceLogo: String,
    @ColumnInfo(name = "title") val title: String
)