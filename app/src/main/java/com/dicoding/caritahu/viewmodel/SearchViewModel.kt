package com.dicoding.caritahu.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dicoding.caritahu.data.Repository

class SearchViewModel(application: Application): ViewModel() {
    private val repository = Repository(application)
}