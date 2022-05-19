package br.com.grupo4.classoverflow.data.model

data class ResponseModel<out T> (
    val data: T? = null,
    val success: Boolean = true,
    val message: String = ""
)