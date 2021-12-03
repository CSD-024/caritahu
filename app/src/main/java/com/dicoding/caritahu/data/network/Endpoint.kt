package com.dicoding.caritahu.data.network

import com.dicoding.caritahu.BuildConfig.NEWS_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {
    @GET("top-headlines")
    suspend fun topHeadlines(
        @Query("country") country: String = "id",
        @Query("apiKey") apiKey: String = NEWS_KEY
    ): Response<HeadlinesResponse>
}