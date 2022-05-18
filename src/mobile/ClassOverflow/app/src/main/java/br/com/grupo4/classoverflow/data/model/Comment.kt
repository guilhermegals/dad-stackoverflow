package br.com.grupo4.classoverflow.data.model

data class Comment(
    val owner: String = "",
    val content: String = "",
    val likes: List<Like> = listOf()
)
