package com.dicoding.caritahu.data.network

data class HeadlinesResponse(
    val articles: List<Article>?,
    val status: String?,
    val totalResults: Int?
)