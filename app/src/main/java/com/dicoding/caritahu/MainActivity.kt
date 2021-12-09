package com.dicoding.caritahu

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dicoding.caritahu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val botNav = binding.bottomNav
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        val navController = navHost.navController

        botNav.setupWithNavController(navController)
        navController.addOnDestinationChangedListener {_, destination, _ ->
            when(destination.id) {
                R.id.newsFragment -> botNav.visibility = View.VISIBLE
                R.id.hoaxFragment -> botNav.visibility = View.VISIBLE
                R.id.bookmarkFragment -> botNav.visibility = View.VISIBLE
                else -> botNav.visibility = View.GONE
            }
        }
    }
}