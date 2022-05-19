package br.com.grupo4.classoverflow.data.repository.implementation

import br.com.grupo4.classoverflow.core.di.DispatcherModule
import br.com.grupo4.classoverflow.data.api.LoginService
import br.com.grupo4.classoverflow.data.model.LoginRequest
import br.com.grupo4.classoverflow.data.model.ResponseModel
import br.com.grupo4.classoverflow.data.model.Token
import br.com.grupo4.classoverflow.data.repository.contract.LoginRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val service: LoginService,
    private val sharedPreferencesRepository: SharedPreferencesRepository,
    @DispatcherModule.IoDispatcher val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : LoginRepository {

    override suspend fun login(request: LoginRequest): ResponseModel<Token> =
        withContext(dispatcher) {
            try {
                val response = service.login(request)
                val resultBody = response.body()

                if (response.isSuccessful && resultBody != null && resultBody.token != null) {
                    sharedPreferencesRepository.setAccessToken(resultBody.token.bearer)
                    return@withContext ResponseModel(resultBody.token)
                } else {
                    sharedPreferencesRepository.setAccessToken("")
                    return@withContext ResponseModel(
                        success = false,
                        message = "E-mail ou senha inválido"
                    )
                }
            } catch (httpException: HttpException) {
                sharedPreferencesRepository.setAccessToken("")
                return@withContext ResponseModel(success = false, message = httpException.message())
            } catch (exception: Exception) {
                sharedPreferencesRepository.setAccessToken("")
                return@withContext ResponseModel(success = false, message = "Erro desconhecido")
            }
        }
}