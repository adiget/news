package com.ags.annada.newsapp.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ags.annada.newsapp.R
import com.ags.annada.newsapp.repository.ContentRepository
import com.ags.annada.newsapp.room.daos.ContentDao
import com.ags.annada.newsapp.room.entities.Content
import com.ags.annada.newsapp.ui.NewsListAdapter
import kotlinx.coroutines.*

class ContentListViewModel(contentDao: ContentDao) : ViewModel() {

    val newsListAdapter: NewsListAdapter = NewsListAdapter()

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadNews() }

    var repository: ContentRepository = ContentRepository(contentDao)
    val allContents: LiveData<List<Content>> = repository.allContents

    private val viewModelJob = Job()
    private val errorHandler = CoroutineExceptionHandler { _, error ->
        onRetrieveNewsFinish()
        when (error) {
            is Exception -> onRetrievePostsError()
        }
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob + errorHandler)

    init {
        loadNews()
    }

    private fun loadNews() {
        uiScope.launch {
            onRetrieveNewsStart()

            repository.fetchAllContents()

            onRetrieveNewsFinish()
        }
    }

    override fun onCleared() {
        super.onCleared()

        viewModelJob.cancel()
    }

    private suspend fun onRetrieveNewsStart() {
        withContext(Dispatchers.Main) {
            loadingVisibility.value = View.VISIBLE
            errorMessage.value = null
        }
    }

    private fun onRetrieveNewsFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostsError() {
        errorMessage.value = R.string.loading_error
    }
}