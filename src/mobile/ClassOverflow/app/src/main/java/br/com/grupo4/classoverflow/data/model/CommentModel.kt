package br.com.grupo4.classoverflow.data.model

data class CommentModel(
    val _id: String = "",
    val ownerName: String = "",
    val ownerEmail: String = "",
    val content: String = "",
    val createdAt: String = "",
    val likes: List<LikeModel> = listOf()
)
