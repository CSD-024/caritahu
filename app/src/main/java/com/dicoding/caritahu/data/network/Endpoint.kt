package com.dicoding.caritahu.data.network

import com.dicoding.caritahu.data.network.model.Article
import retrofit2.Response
import retrofit2.http.GET

interface Endpoint {
    @GET("news/headlines")
    suspend fun topHeadlines(): Response<List<Article>>
}