package com.hugo.nytimesnews.app.domain.model

data class Article(
    val section: String,
    val title: String,
    val abstract: String,
    val uri: String,
    val url: String,
    val byline: String,
    val publishedDate: String
)