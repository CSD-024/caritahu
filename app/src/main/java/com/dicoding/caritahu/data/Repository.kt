package com.dicoding.caritahu.data

import com.dicoding.caritahu.data.network.Client
import com.dicoding.caritahu.data.network.HeadlinesResponse
import retrofit2.Response

object Repository {
    suspend fun topHeadlines(): Response<HeadlinesResponse> {
        return Client.retrofit.topHeadlines()
    }
}