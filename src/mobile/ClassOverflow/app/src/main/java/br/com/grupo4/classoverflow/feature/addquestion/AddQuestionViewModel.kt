package br.com.grupo4.classoverflow.feature.addquestion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.grupo4.classoverflow.core.EventHandler
import br.com.grupo4.classoverflow.core.di.DispatcherModule.IoDispatcher
import br.com.grupo4.classoverflow.data.model.QuestionModel
import br.com.grupo4.classoverflow.data.repository.contract.QuestionRepository
import br.com.grupo4.classoverflow.data.repository.implementation.SharedPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddQuestionViewModel @Inject constructor(
    private val questionRepository: QuestionRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    //region [ Properties ]

    private var _id: String? = null
    private lateinit var _question: QuestionModel

    //endregion

    //region [ Live Datas ]

    private val _backEvent = MutableLiveData<EventHandler<Boolean>>()
    val backEvent: LiveData<EventHandler<Boolean>> get() = _backEvent

    private val _closeEvent = MutableLiveData<EventHandler<Boolean>>()
    val closeEvent: LiveData<EventHandler<Boolean>> get() = _closeEvent

    private val _openQuestionEvent = MutableLiveData<EventHandler<String>>()
    val openQuestionEvent: LiveData<EventHandler<String>> get() = _openQuestionEvent

    private val _errorEvent = MutableLiveData<EventHandler<Boolean>>()
    val errorEvent: LiveData<EventHandler<Boolean>> get() = _errorEvent

    private val _invalidFieldEvent = MutableLiveData<EventHandler<Boolean>>()
    val invalidFieldEvent: LiveData<EventHandler<Boolean>> get() = _invalidFieldEvent

    private val _isEditMode = MutableLiveData(false)
    val isEditMode: LiveData<Boolean> = _isEditMode

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    val title = MutableLiveData("")
    val description = MutableLiveData("")
    val subject = MutableLiveData("")
    val topics = MutableLiveData("")

    //endregion

    //region [ Properties ]

    fun back() {
        _backEvent.postValue(EventHandler(true))
    }

    fun setId(id: String?) {
        if (!id.isNullOrEmpty()) {
            _isEditMode.postValue(true)
            _id = id
            getQuestion(id)
        }
    }

    fun save() {
        if (!isValid()) {
            _invalidFieldEvent.postValue(EventHandler(true))
            return
        }

        _id.let { id ->
            if (id.isNullOrBlank()) addQuestion()
            else editQuestion(id)
        }
    }

    fun delete() {
        _id?.let { id ->
            deleteQuestion(id)
        }
    }

    //endregion

    //region [ Private Functions ]

    private fun getQuestion(id: String) {
        viewModelScope.launch(dispatcher) {
            _isLoading.postValue(true)

            val response = questionRepository.get(id)
            if (response.success && response.data != null) {
                response.data.let { question ->
                    _question = question
                    title.postValue(question.title)
                    description.postValue(question.content)
                    subject.postValue(question.subject)
                    topics.postValue(question.topic.joinToString(separator = " "))
                }
            } else {
                _errorEvent.postValue(EventHandler(true))
            }
            _isLoading.postValue(false)
        }
    }

    private fun addQuestion() {
        viewModelScope.launch(dispatcher) {
            _isLoading.postValue(true)

            val response = questionRepository.add(
                QuestionModel(
                    title = getTitle(),
                    content = getDescription(),
                    subject = getSubject(),
                    topic = getTopics()
                )
            )
            if (response.success && response.data != null) {
                clear()
                _openQuestionEvent.postValue(EventHandler(response.data._id ?: ""))
            } else {
                _errorEvent.postValue(EventHandler(true))
            }

            _isLoading.postValue(false)
        }
    }

    private fun editQuestion(id: String) {
        viewModelScope.launch(dispatcher) {
            _isLoading.postValue(true)

            val response = questionRepository.edit(
                id,
                QuestionModel(
                    title = getTitle(),
                    content = getDescription(),
                    subject = getSubject(),
                    topic = getTopics(),
                    isActive = _question.isActive,
                    createdAt = _question.createdAt,
                    ownerEmail = _question.ownerEmail,
                    ownerName = _question.ownerName,
                    comments = _question.comments
                )
            )
            if (response.success && response.data != null) {
                clear()
                _openQuestionEvent.postValue(EventHandler(response.data._id ?: ""))
            } else {
                _errorEvent.postValue(EventHandler(true))
            }

            _isLoading.postValue(false)
        }
    }

    private fun deleteQuestion(id: String) {
        viewModelScope.launch(dispatcher) {
            _isLoading.postValue(true)

            val response = questionRepository.delete(id)
            if (response.success) {
                _closeEvent.postValue(EventHandler(true))
            } else {
                _errorEvent.postValue(EventHandler(true))
            }

            _isLoading.postValue(false)
        }
    }

    private fun clear() {
        title.postValue("")
        description.postValue("")
        subject.postValue("")
        topics.postValue("")
    }

    private fun isValid(): Boolean {
        return getTitle().isNotBlank() &&
                getDescription().isNotBlank() &&
                getSubject().isNotBlank() &&
                getTopics().isNotEmpty()
    }

    private fun getTitle(): String = title.value ?: ""
    private fun getDescription(): String = description.value ?: ""
    private fun getSubject(): String = subject.value ?: ""
    private fun getTopics(): List<String> = topics.value?.split(" ")?.toList() ?: listOf()

    //endregion
}