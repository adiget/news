package com.ags.annada.newsapp.api

import com.ags.annada.newsapp.room.entities.Content
import com.ags.annada.newsapp.room.entities.News
import retrofit2.http.GET

interface ApiService {
    @GET("309PryD")
    suspend fun getContents(): News
}