package com.ags.annada.newsapp.repository

import com.ags.annada.newsapp.api.ApiService
import com.ags.annada.newsapp.room.daos.ContentDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContentRepository (val contentDao: ContentDao): BaseRepository() {
    @Inject
    lateinit var api: ApiService

    val allContents = contentDao.all

    fun fetchAllContents() {
        CoroutineScope(Dispatchers.IO).launch{
            if (allContents.value.isNullOrEmpty()) {
                val contents = api.getContents().content
                contentDao.insertAll(content = *contents.toTypedArray())
            }
        }
    }
}