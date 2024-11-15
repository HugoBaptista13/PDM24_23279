package com.hugo.nytimesnews.app.domain.repository

import com.hugo.nytimesnews.app.domain.model.Article

interface ArticleRepository {
    suspend fun getTopStories(section: String) : List<Article>
    suspend fun getArticleDetails(uri: String) : Article
}