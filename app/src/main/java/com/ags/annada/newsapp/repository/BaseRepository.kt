package com.ags.annada.newsapp.repository

import com.ags.annada.newsapp.dagger.component.DaggerRepositoryInjector
import com.ags.annada.newsapp.dagger.component.RepositoryInjector
import com.ags.annada.newsapp.dagger.module.NetworkModule


abstract class BaseRepository {
    private val injector: RepositoryInjector = DaggerRepositoryInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is ContentRepository -> injector.inject(this)
        }
    }
}