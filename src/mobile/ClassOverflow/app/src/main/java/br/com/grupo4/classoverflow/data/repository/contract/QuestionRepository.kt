package br.com.grupo4.classoverflow.data.repository.contract

import br.com.grupo4.classoverflow.data.model.QuestionModel
import br.com.grupo4.classoverflow.data.model.ResponseModel

interface QuestionRepository {
    suspend fun getAll(): ResponseModel<List<QuestionModel>>
}