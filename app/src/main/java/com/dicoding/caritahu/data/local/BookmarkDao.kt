package com.dicoding.caritahu.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dicoding.caritahu.data.network.model.NewsArticle

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM bookmark")
    fun getAll(): LiveData<List<NewsArticle>>

    @Query("SELECT * FROM bookmark WHERE title = :title")
    fun searchBookmark(title: String): NewsArticle

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(article: NewsArticle)

    @Delete
    fun remove(article: NewsArticle)
}