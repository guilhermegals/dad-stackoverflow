package br.com.grupo4.classoverflow.data.model

data class QuestionModel(
    val _id: String? = null,
    val title: String = "",
    val content: String = "",
    val isActive: Boolean = true,
    val createdAt: String = "",
    val subject: String = "",
    val ownerName: String = "",
    val ownerEmail: String = "",
    val topic: List<String> = listOf(),
    val comments: List<CommentModel> = listOf()
)