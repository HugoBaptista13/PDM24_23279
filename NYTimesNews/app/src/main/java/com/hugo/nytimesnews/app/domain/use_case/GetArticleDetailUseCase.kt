package com.hugo.nytimesnews.app.domain.use_case

import com.hugo.nytimesnews.app.domain.model.Article
import com.hugo.nytimesnews.app.domain.repository.ArticleRepository

class GetArticleDetailUseCase(private val repository: ArticleRepository) {
    suspend operator fun invoke(uri: String): Article {
        return repository.getArticleDetails(uri)
    }
}