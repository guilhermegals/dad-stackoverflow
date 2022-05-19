package br.com.grupo4.classoverflow.data.api

import br.com.grupo4.classoverflow.data.model.LoginRequest
import br.com.grupo4.classoverflow.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {

    @POST("v1/auth")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}