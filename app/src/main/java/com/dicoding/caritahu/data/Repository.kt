package com.dicoding.caritahu.data

import com.dicoding.caritahu.data.network.Client
import com.dicoding.caritahu.data.network.model.Article
import retrofit2.Response

object Repository {
    suspend fun topHeadlines(): Response<List<Article>> {
        return Client.retrofit.topHeadlines()
    }
}