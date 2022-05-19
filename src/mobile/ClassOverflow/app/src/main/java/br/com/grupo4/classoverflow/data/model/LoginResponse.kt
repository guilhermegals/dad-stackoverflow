package br.com.grupo4.classoverflow.data.model

data class LoginResponse(
    val token: Token? = null
)

data class Token (
    val id: Int,
    val bearer: String
)