package com.dicoding.caritahu.data

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.caritahu.data.local.AppDatabase
import com.dicoding.caritahu.data.local.BookmarkDao
import com.dicoding.caritahu.data.network.Client
import com.dicoding.caritahu.data.network.model.HoaxArticle
import com.dicoding.caritahu.data.network.model.HoaxAuthor
import com.dicoding.caritahu.data.network.model.NewsArticle
import com.dicoding.caritahu.data.network.model.NewsSource
import retrofit2.Response
import java.util.concurrent.Executors

class Repository(application: Application) {
    private val dao: BookmarkDao
    private val executorService = Executors.newSingleThreadExecutor()

    init {
        val db = AppDatabase.getDatabase(application)
        dao = db.dao()
    }

    fun getAll(): LiveData<List<NewsArticle>> = dao.getAll()

    fun getBookmarked(title: String): NewsArticle = dao.searchBookmark(title)

    fun insert(article: NewsArticle) {
        executorService.execute { dao.insert(article) }
    }

    fun remove(article: NewsArticle) {
        executorService.execute { dao.remove(article) }
    }

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

    suspend fun hoaxAuthors(): Response<List<HoaxAuthor>> {
        return Client.retrofit.hoaxAuthors()
    }

    suspend fun hoaxAuthor(id: String): Response<HoaxAuthor> {
        return Client.retrofit.hoaxAuthor(id)
    }
}