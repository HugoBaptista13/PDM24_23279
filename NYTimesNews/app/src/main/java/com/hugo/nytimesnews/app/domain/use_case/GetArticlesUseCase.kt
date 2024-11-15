package com.hugo.nytimesnews.app.domain.use_case

import com.hugo.nytimesnews.app.domain.model.Article
import com.hugo.nytimesnews.app.domain.repository.ArticleRepository

class GetArticlesUseCase(private val repository: ArticleRepository) {
    suspend operator fun invoke(section: String): List<Article> {
        return repository.getTopStories(section)
    }
}
