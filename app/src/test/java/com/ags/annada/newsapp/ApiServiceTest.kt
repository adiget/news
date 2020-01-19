package com.ags.annada.newsapp

import com.ags.annada.newsapp.api.ApiService
import com.ags.annada.newsapp.api.Contracts.Companion.BASE_URL
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceTest {
    lateinit var service: ApiService

    @Before
    fun setUp() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(ApiService::class.java)
    }

    @Test
    fun should_callServiceWithCoroutine() {
        runBlocking {
            val newsContents = service.getContents().content

            Assert.assertTrue(newsContents.isNotEmpty())
        }
    }
}