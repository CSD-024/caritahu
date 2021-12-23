package com.dicoding.caritahu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.caritahu.view.settings.SettingPreferences
import kotlinx.coroutines.launch


class SettingsViewModel(private val pref: SettingPreferences): ViewModel() {
    fun getThemeSetting(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isDarkModeActive: Boolean){
            viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }
}