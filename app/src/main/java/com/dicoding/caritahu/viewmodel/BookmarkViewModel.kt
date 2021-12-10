package com.dicoding.caritahu.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.caritahu.data.Repository
import com.dicoding.caritahu.data.network.model.NewsArticle

class BookmarkViewModel(application: Application): ViewModel() {
    private val repository = Repository(application)

    fun getAll(): LiveData<List<NewsArticle>> = repository.getAll()
}