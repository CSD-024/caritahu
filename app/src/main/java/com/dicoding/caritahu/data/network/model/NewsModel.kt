package com.dicoding.caritahu.data.network.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Entity(tableName = "bookmark")
@Parcelize
data class NewsArticle(
    @PrimaryKey
    val title: String,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: @RawValue Source?,
    val url: String?,
    val urlToImage: String?
) : Parcelable

data class Source(
    val name: String?
)

data class NewsSource(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String,
)