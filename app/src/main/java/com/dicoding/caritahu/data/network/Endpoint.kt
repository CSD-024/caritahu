package com.dicoding.caritahu.data.network

import retrofit2.Response
import retrofit2.http.GET

interface Endpoint {
    @GET("top-headlines?country=id&apiKey=509034bcdc454d05a68399a615aaa450")
    suspend fun topHeadlines(): Response<HeadlinesResponse>
}