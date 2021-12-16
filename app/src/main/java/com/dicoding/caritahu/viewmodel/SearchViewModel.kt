package com.dicoding.caritahu.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.caritahu.data.Repository
import com.dicoding.caritahu.data.network.model.HoaxArticle
import com.dicoding.caritahu.data.network.model.NewsArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(application: Application): ViewModel() {
    private val repository = Repository(application)

    private var _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private var _searchHoax = MutableLiveData<List<HoaxArticle>?>()
    val searchHoax: LiveData<List<HoaxArticle>?> = _searchHoax

    private var _searchNews = MutableLiveData<List<NewsArticle>>()
    val searchNews: LiveData<List<NewsArticle>> = _searchNews

    fun getSearchHoax(query: String){
        _status.value = "Loading"
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.searchHoax(query)
            if (response.isSuccessful) {
                val data = response.body()
                println(data)
                _status.postValue("Success")
                if (data != null) {
                    if (data.isNotEmpty()) {
                        _searchHoax.postValue(data)
                    } else {
                        _status.postValue("Hasil pencarian Kosong")
                    }
                } else {
                    _status.postValue("Terjadi error tidak terduga")
                }
            }
        }
    }

    fun getSearchNews(query: String){
        _status.value = "Loading"
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.searchNews(query)
            if (response.isSuccessful) {
                val data = response.body()
                println(data)
                _status.postValue("Success")
                if (data != null) {
                    if (data.isNotEmpty()) {
                        _searchNews.postValue(data!!)
                    } else {
                        _status.postValue("Hasil pencarian Kosong")
                    }
                } else {
                    _status.postValue("Terjadi error tidak terduga")
                }
            }
        }
    }
}