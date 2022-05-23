package br.com.grupo4.classoverflow.feature.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.grupo4.classoverflow.core.EventHandler
import br.com.grupo4.classoverflow.core.di.DispatcherModule.IoDispatcher
import br.com.grupo4.classoverflow.data.model.CommentLikeRequest
import br.com.grupo4.classoverflow.data.model.CommentRequest
import br.com.grupo4.classoverflow.data.model.QuestionModel
import br.com.grupo4.classoverflow.data.repository.contract.QuestionRepository
import br.com.grupo4.classoverflow.data.repository.implementation.SharedPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionDetailViewModel @Inject constructor(
    private val questionRepository: QuestionRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private var _id: String = ""

    private val _backEvent = MutableLiveData<EventHandler<Boolean>>()
    val backEvent: LiveData<EventHandler<Boolean>> get() = _backEvent

    private val _errorEvent = MutableLiveData<EventHandler<Boolean>>()
    val errorEvent: LiveData<EventHandler<Boolean>> get() = _errorEvent

    private val _likeErrorEvent = MutableLiveData<EventHandler<Boolean>>()
    val likeErrorEvent: LiveData<EventHandler<Boolean>> get() = _likeErrorEvent

    private val _likeFeedbackEvent = MutableLiveData<EventHandler<Boolean>>()
    val likeEvent: LiveData<EventHandler<Boolean>> get() = _likeFeedbackEvent

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isLoadingComment = MutableLiveData(false)
    val isLoadingComment: LiveData<Boolean> = _isLoadingComment

    private val _question = MutableLiveData<QuestionModel>()
    val question: LiveData<QuestionModel> = _question

    val comment: MutableLiveData<String> = MutableLiveData<String>()

    fun getQuestion(id: String) {
        viewModelScope.launch(dispatcher) {
            _isLoading.postValue(true)

            _id = id
            val response = questionRepository.get(id)
            if (response.success && response.data != null) {
                response.data.let { question ->
                    _question.postValue(question)
                }

            } else {
                _errorEvent.postValue(EventHandler(true))
            }
            _isLoading.postValue(false)
        }
    }

    fun getCurrentUserEmail(): String = sharedPreferencesRepository.getUserInformation().email

    fun addComment() {
        viewModelScope.launch(dispatcher) {
            _isLoadingComment.postValue(true)

            comment.value?.let { comment ->
                questionRepository.addComment(_id, CommentRequest(comment))
            }

            _isLoadingComment.postValue(false)
            comment.postValue("")

            getQuestion(_id)
        }
    }

    fun like(commentId: String, liked: Boolean) {
        viewModelScope.launch(dispatcher) {
            val result =
                questionRepository.addCommentLike(_id, CommentLikeRequest(commentId, liked))
            if (result.success) {
                _likeFeedbackEvent.postValue(EventHandler(liked))
            } else {
                _likeErrorEvent.postValue(EventHandler(true))
            }
        }
    }

    fun back() {
        _backEvent.postValue(EventHandler(true))
    }
}