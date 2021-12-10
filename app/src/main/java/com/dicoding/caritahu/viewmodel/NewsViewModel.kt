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

class NewsViewModel(application: Application): ViewModel() {
    private val repository = Repository(application)

    private var _headlines: MutableLiveData<List<NewsArticle>> = MutableLiveData()
    val headlines: LiveData<List<NewsArticle>> = _headlines

    fun topHeadlines(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.topHeadlines()
            if (response.isSuccessful) {
                val data = response.body()
                _headlines.postValue(data!!)
            }
        }
    }
}