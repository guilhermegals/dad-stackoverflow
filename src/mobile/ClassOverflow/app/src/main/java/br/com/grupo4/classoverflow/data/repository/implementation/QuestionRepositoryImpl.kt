package br.com.grupo4.classoverflow.data.repository.implementation

import br.com.grupo4.classoverflow.core.di.DispatcherModule
import br.com.grupo4.classoverflow.data.api.QuestionService
import br.com.grupo4.classoverflow.data.model.QuestionModel
import br.com.grupo4.classoverflow.data.model.ResponseModel
import br.com.grupo4.classoverflow.data.repository.contract.QuestionRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuestionRepositoryImpl @Inject constructor(
    private val service: QuestionService,
    @DispatcherModule.IoDispatcher val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseRepository(), QuestionRepository {

    override suspend fun getAll(): ResponseModel<List<QuestionModel>> = withContext(dispatcher) {
        return@withContext doRequest {
            service.getAll()
        }
    }
}