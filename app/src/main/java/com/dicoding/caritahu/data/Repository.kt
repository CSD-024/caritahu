package com.dicoding.caritahu.data

import com.dicoding.caritahu.data.network.Client
import com.dicoding.caritahu.data.network.model.HoaxArticle
import com.dicoding.caritahu.data.network.model.HoaxAuthor
import com.dicoding.caritahu.data.network.model.NewsArticle
import com.dicoding.caritahu.data.network.model.NewsSource
import retrofit2.Response

object Repository {
    suspend fun topHeadlines(): Response<List<NewsArticle>> {
        return Client.retrofit.newsHeadlines()
    }

    suspend fun searchNews(query: String): Response<List<NewsArticle>> {
        return Client.retrofit.newsSearch(query)
    }

    suspend fun newsSource(): Response<List<NewsSource>> {
        return Client.retrofit.newsSources()
    }

    suspend fun latestHoax(): Response<List<HoaxArticle>> {
        return Client.retrofit.hoaxLatest()
    }

    suspend fun searchHoax(query: String): Response<List<HoaxArticle>> {
        return Client.retrofit.hoaxSearch(query)
    }

    suspend fun hoaxAuthor(): Response<List<HoaxAuthor>> {
        return Client.retrofit.hoaxAuthors()
    }
}