package com.ags.annada.newsapp.room.entities

data class News(
    val content: List<Content>,
    val totalItems: Int
)