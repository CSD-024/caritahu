package com.dicoding.caritahu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.caritahu.adapter.NewsAdapter
import com.dicoding.caritahu.data.network.model.NewsArticle
import com.dicoding.caritahu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.topHeadlines()

        viewModel.headlines.observe(this, { articles ->
            if (articles.isNotEmpty()){
                setupRV(articles)
            }
        })
    }

    private fun setupRV(news: List<NewsArticle>) {
        val adapter = NewsAdapter()
        adapter.setData(news)
        binding.rvNews.apply {
            this.adapter = adapter
            setHasFixedSize(true)
        }
    }
}