package br.com.grupo4.classoverflow.data.repository.contract

import br.com.grupo4.classoverflow.data.model.CommentLikeRequest
import br.com.grupo4.classoverflow.data.model.CommentRequest
import br.com.grupo4.classoverflow.data.model.QuestionModel
import br.com.grupo4.classoverflow.data.model.ResponseModel

interface QuestionRepository {
    suspend fun getAll(): ResponseModel<List<QuestionModel>>
    suspend fun get(id: String): ResponseModel<QuestionModel>
    suspend fun addComment(id: String, request: CommentRequest): ResponseModel<Any>
    suspend fun addCommentLike(id: String, request: CommentLikeRequest): ResponseModel<Any>
}