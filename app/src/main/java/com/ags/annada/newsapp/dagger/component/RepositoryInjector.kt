package com.ags.annada.newsapp.dagger.component

import com.ags.annada.newsapp.dagger.module.NetworkModule
import com.ags.annada.newsapp.repository.ContentRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface RepositoryInjector{
    fun inject(contentRepository: ContentRepository)

    @Component.Builder
    interface Builder {
        fun build(): RepositoryInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}