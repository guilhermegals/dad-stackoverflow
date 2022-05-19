package br.com.grupo4.classoverflow.data.model

data class CommentModel(
    val owner: String = "",
    val content: String = "",
    val likes: List<LikeModel> = listOf()
)
