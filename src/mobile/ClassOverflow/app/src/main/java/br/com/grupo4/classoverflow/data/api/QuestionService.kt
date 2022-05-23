package br.com.grupo4.classoverflow.data.api

import br.com.grupo4.classoverflow.data.model.CommentLikeRequest
import br.com.grupo4.classoverflow.data.model.CommentRequest
import br.com.grupo4.classoverflow.data.model.QuestionModel
import retrofit2.Response
import retrofit2.http.*

interface QuestionService {

    @GET("v1/posts")
    suspend fun getAll(): Response<List<QuestionModel>>

    @GET("v1/posts/{id}")
    suspend fun get(@Path("id") id: String): Response<QuestionModel>

    @POST("v1/posts/{id}/comments")
    suspend fun addComment(@Path("id") id: String, @Body request: CommentRequest): Response<Any>

    @PUT("v1/posts/{id}/comments/like")
    suspend fun addCommentLike(@Path("id") id: String, @Body request: CommentLikeRequest): Response<Any>
}