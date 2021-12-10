package com.dicoding.caritahu.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.caritahu.data.Repository
import com.dicoding.caritahu.data.network.model.NewsArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application): ViewModel() {
    private val repository = Repository(application)

    private var _isBookmarked: MutableLiveData<Boolean> = MutableLiveData()
    val isBookmarked: LiveData<Boolean> = _isBookmarked

    fun getBookmarked(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val article = repository.getBookmarked(title)
            print(article)
            _isBookmarked.postValue(article != null)
        }
    }

    fun insert(article: NewsArticle) {
        repository.insert(article)
        getBookmarked(article.title)
    }

    fun remove(article: NewsArticle) {
        repository.remove(article)
        getBookmarked(article.title)
    }
}