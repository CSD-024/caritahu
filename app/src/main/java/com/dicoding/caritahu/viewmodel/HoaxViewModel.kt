package com.dicoding.caritahu.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.caritahu.data.Repository
import com.dicoding.caritahu.data.network.model.HoaxArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HoaxViewModel(application: Application): ViewModel() {
    private val repository = Repository(application)

    private var _latestHoax: MutableLiveData<List<HoaxArticle>> = MutableLiveData()
    val latestHoax: LiveData<List<HoaxArticle>> get() = _latestHoax

    private var _status: MutableLiveData<Boolean> = MutableLiveData()
    val status: LiveData<Boolean> = _status

    fun getLatestHoax(){
        _status.value = false
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.latestHoax()
            if (response.isSuccessful) {
                val data = response.body()
                _status.postValue(true)
                _latestHoax.postValue(data!!)
            }
        }
    }
}