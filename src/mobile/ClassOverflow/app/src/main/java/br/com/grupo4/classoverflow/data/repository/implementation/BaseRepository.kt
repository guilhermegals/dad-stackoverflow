package br.com.grupo4.classoverflow.data.repository.implementation

import br.com.grupo4.classoverflow.data.model.ResponseModel
import retrofit2.HttpException
import retrofit2.Response
import java.lang.Exception

abstract class BaseRepository {

    protected suspend fun <T> doRequest(request: suspend ()-> Response<T>) : ResponseModel<T>{
        return try {
            val response = request()
            val resultBody = response.body()

            if (response.isSuccessful && resultBody != null) {
                ResponseModel(data = resultBody)
            } else {
                ResponseModel(success = false, message = response.message())
            }
        } catch (httpException: HttpException) {
            ResponseModel(success = false, message = httpException.message())
        } catch (exception: Exception) {
            ResponseModel(success = false, message = "Erro desconhecido.")
        }
    }
}