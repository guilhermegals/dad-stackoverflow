package br.com.grupo4.classoverflow.data.api

import br.com.grupo4.classoverflow.data.model.QuestionModel
import retrofit2.Response
import retrofit2.http.GET

interface QuestionService {

    @GET("v1/posts")
    suspend fun getAll(): Response<List<QuestionModel>>
}