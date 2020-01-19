package com.ags.annada.newsapp.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.ags.annada.newsapp.room.NewsDatabase


class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ContentListViewModel::class.java)) {
            val db =
                Room.databaseBuilder(activity.applicationContext, NewsDatabase::class.java, "news")
                    .build()
            @Suppress("UNCHECKED_CAST")
            return ContentListViewModel(db.contentDao()) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}