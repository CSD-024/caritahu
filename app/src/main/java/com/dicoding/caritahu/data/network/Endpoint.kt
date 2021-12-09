package com.dicoding.caritahu.data.network

import com.dicoding.caritahu.data.network.model.HoaxArticle
import com.dicoding.caritahu.data.network.model.HoaxAuthor
import com.dicoding.caritahu.data.network.model.NewsArticle
import com.dicoding.caritahu.data.network.model.NewsSource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Endpoint {
    @GET("news/headlines")
    suspend fun newsHeadlines(): Response<List<NewsArticle>>

    @GET("news/search/{q}")
    suspend fun newsSearch(@Path("q") q: String): Response<List<NewsArticle>>

    @GET("news/sources")
    suspend fun newsSources(): Response<List<NewsSource>>

    @GET("hoax/latest")
    suspend fun hoaxLatest(): Response<List<HoaxArticle>>

    @GET("hoax/search/{q}")
    suspend fun hoaxSearch(@Path("q") q: String): Response<List<HoaxArticle>>

    @GET("hoax/authors")
    suspend fun hoaxAuthors(): Response<List<HoaxAuthor>>

    @GET("hoax/authors/{id}")
    suspend fun hoaxAuthor(@Path("id") id: String): Response<HoaxAuthor>
}