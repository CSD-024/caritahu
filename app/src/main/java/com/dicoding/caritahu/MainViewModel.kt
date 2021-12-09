package com.dicoding.caritahu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.caritahu.data.Repository
import com.dicoding.caritahu.data.network.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    private var _headlines: MutableLiveData<List<Article>> = MutableLiveData()
    val headlines: LiveData<List<Article>> = _headlines



    fun topHeadlines(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = Repository.topHeadlines()
            if (response.isSuccessful) {
                val data = response.body()
                _headlines.postValue(data!!)
            }
        }
    }
}