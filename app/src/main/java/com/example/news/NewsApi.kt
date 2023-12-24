package com.example.news

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("news")
    suspend fun getNews(
        @Query("apiKey") apiKey: String,
        @Query("q") keyword: String
    ): NewsResponse
}