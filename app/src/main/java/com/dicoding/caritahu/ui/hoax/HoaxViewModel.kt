package com.dicoding.caritahu.ui.hoax

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.caritahu.data.Repository
import com.dicoding.caritahu.data.network.model.HoaxArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HoaxViewModel : ViewModel() {
    private var _latestHoax: MutableLiveData<List<HoaxArticle>> = MutableLiveData()
    val latestHoax: LiveData<List<HoaxArticle>> get() = _latestHoax

    fun getLatestHoax(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = Repository.latestHoax()
            if (response.isSuccessful) {
                val data = response.body()
                _latestHoax.postValue(data!!)
            }
        }
    }
}