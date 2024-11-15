package com.hugo.nytimesnews.app.data.remote.api

import com.hugo.nytimesnews.app.data.remote.model.ArticleDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface NyTimesApi {
    @GET("/svc/topstories/v2/{section}.json?api-key=mGd7NKeXp48VRobzf9oA7xDt0vo4eU9D")
    suspend fun getTopStories(@Path("section") section: String): List<ArticleDto>
    suspend fun getArticleDetails(uri: String): ArticleDto
}

object RetrofitInstance {
    val api: NyTimesApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.nytimes.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NyTimesApi::class.java)
    }
}