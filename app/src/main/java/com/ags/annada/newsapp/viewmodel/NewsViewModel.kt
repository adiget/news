package com.ags.annada.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ags.annada.newsapp.room.entities.Content

class NewsViewModel : ViewModel() {
    val title = MutableLiveData<String>()
    val contentSourceLogo = MutableLiveData<String>()

    fun bind(content: Content) {
        title.value = content.title
        contentSourceLogo.value = content.contentSourceLogo
    }

    fun getcontentSourceLogo(): String {
        return contentSourceLogo.value.toString()
    }

}