package br.com.grupo4.classoverflow.data.repository.contract

import br.com.grupo4.classoverflow.data.model.LoginRequest
import br.com.grupo4.classoverflow.data.model.ResponseModel

interface LoginRepository {
    suspend fun login(request: LoginRequest): ResponseModel<String>
}