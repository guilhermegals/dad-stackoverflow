package br.com.grupo4.classoverflow.data.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("login")
    val email: String,
    @SerializedName("password")
    val password: String
)