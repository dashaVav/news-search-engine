package com.example.news

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val results: List<Article>
)
