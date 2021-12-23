package com.dicoding.caritahu

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.dicoding.caritahu.databinding.ActivityMainBinding
import com.dicoding.caritahu.view.settings.SettingPreferences
import com.dicoding.caritahu.viewmodel.SettingsViewModel
import com.dicoding.caritahu.viewmodel.ViewModelFactorySettings

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val botNav  = binding.bottomNav
        val chipNav = binding.chipNav
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        navController = navHost.navController

        chipNav.setOnItemSelectedListener{ id -> botNav.selectedItemId = id }
        NavigationUI.setupWithNavController(botNav, navController)

        navController.addOnDestinationChangedListener {_, destination, _ ->
            when(destination.id) {
                R.id.newsFragment -> botNav.visibility = View.VISIBLE
                R.id.hoaxFragment -> botNav.visibility = View.VISIBLE
                R.id.bookmarkFragment -> botNav.visibility = View.VISIBLE
                R.id.settingsFragment -> botNav.visibility = View.VISIBLE
                else -> {
                    botNav.visibility = View.GONE
                    chipNav.visibility = View.GONE
                }
            }
            chipNav.setItemSelected(destination.id)
        }

        val pref = SettingPreferences.getInstance(dataStore)
        viewModel = ViewModelProvider(this, ViewModelFactorySettings(pref))[SettingsViewModel::class.java]
        viewModel.getThemeSetting().observe(
            this,
            {isDarkModeActive: Boolean ->
                if (isDarkModeActive){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        )
    }
}