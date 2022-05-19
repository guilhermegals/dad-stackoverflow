package br.com.grupo4.classoverflow.data.model

import java.time.LocalDateTime

data class QuestionModel(
    val title: String = "",
    val content: String = "",
    val isActive: Boolean = true,
    val createdAt: LocalDateTime? = null,
    val updatedAt: LocalDateTime? = null,
    val subject: String = "",
    val owner: String = "", // user id
    val liked: Boolean = false,
    val topic: List<String> = listOf(),
    val comments: List<CommentModel> = listOf()
)