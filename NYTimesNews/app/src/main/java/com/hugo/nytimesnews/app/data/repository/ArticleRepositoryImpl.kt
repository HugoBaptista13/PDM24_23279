package com.hugo.nytimesnews.app.data.repository

import com.hugo.nytimesnews.app.data.remote.api.NyTimesApi
import com.hugo.nytimesnews.app.domain.model.Article
import com.hugo.nytimesnews.app.domain.repository.ArticleRepository

class ArticleRepositoryImpl(private val api : NyTimesApi) : ArticleRepository {
    override suspend fun getTopStories(section: String): List<Article> {
        return api.getTopStories(section).map { it.toArticle() }
    }

    override suspend fun getArticleDetails(uri: String): Article {
        return api.getArticleDetails(uri).toArticle()
    }
}