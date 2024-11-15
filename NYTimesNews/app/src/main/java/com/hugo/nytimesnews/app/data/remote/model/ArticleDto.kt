package com.hugo.nytimesnews.app.data.remote.model

import com.hugo.nytimesnews.app.domain.model.Article

data class ArticleDto(
    val section: String,
    val title: String,
    val abstract: String,
    val uri: String,
    val url: String,
    val byline: String,
    val publishedDate: String
) {
    fun toArticle() : Article{
        return Article(
            section = this.section,
            title = this.title,
            abstract = this.abstract,
            uri = this.uri,
            url = this.url,
            byline = this.byline,
            publishedDate = this.publishedDate)
    }
}