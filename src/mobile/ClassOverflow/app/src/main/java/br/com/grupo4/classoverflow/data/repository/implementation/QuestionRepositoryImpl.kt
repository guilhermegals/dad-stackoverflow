package br.com.grupo4.classoverflow.data.repository.implementation

import br.com.grupo4.classoverflow.core.di.DispatcherModule
import br.com.grupo4.classoverflow.data.api.QuestionService
import br.com.grupo4.classoverflow.data.model.CommentLikeRequest
import br.com.grupo4.classoverflow.data.model.CommentRequest
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

    override suspend fun get(id: String): ResponseModel<QuestionModel> = withContext(dispatcher) {
        return@withContext doRequest {
            service.get(id)
        }
    }

    override suspend fun add(model: QuestionModel): ResponseModel<QuestionModel> = withContext(dispatcher) {
        return@withContext doRequest {
            service.add(model)
        }
    }

    override suspend fun edit(id: String, model: QuestionModel): ResponseModel<QuestionModel> = withContext(dispatcher) {
        return@withContext doRequest {
            service.edit(id, model)
        }
    }

    override suspend fun delete(id: String): ResponseModel<Any> = withContext(dispatcher) {
        return@withContext doRequest {
            service.delete(id)
        }
    }

    override suspend fun addComment(id: String, request: CommentRequest): ResponseModel<Any> =
        withContext(dispatcher) {
            return@withContext doRequest {
                service.addComment(id, request)
            }
        }

    override suspend fun addCommentLike(
        id: String,
        request: CommentLikeRequest
    ): ResponseModel<Any> = withContext(dispatcher) {
        return@withContext doRequest {
            service.addCommentLike(id, request)
        }
    }
}