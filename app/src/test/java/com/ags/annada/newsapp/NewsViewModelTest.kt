package com.ags.annada.newsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ags.annada.newsapp.room.entities.Content
import com.ags.annada.newsapp.viewmodel.NewsViewModel
import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentCaptor
import org.mockito.Mockito

@RunWith(JUnit4::class)
class NewsViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: NewsViewModel

    @Before
    @Throws(Exception::class)
    fun setUp() {
        sut = NewsViewModel()
    }

    @Test
    fun `test members updated when model changes`() {
        val content = Content("", "dummy_title")
        sut.bind(content)

        Assert.assertEquals(
            "title should be dummy_title",
            "dummy_title",
            sut.title.value
        )
    }

    @Test
    fun `test livedata notifies when it is observed`() {
        val observer = mock<Observer<String>>()
        sut.title.observeForever(observer)

        val argumentCaptor = ArgumentCaptor.forClass(String::class.java)

        val content = Content("", "dummy_title")
        sut.bind(content)

        argumentCaptor.run {
            Mockito.verify(observer, Mockito.times(1)).onChanged(capture())
        }
    }

}