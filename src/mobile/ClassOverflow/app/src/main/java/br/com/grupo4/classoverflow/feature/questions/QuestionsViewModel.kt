package br.com.grupo4.classoverflow.feature.questions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.grupo4.classoverflow.core.EventHandler
import br.com.grupo4.classoverflow.core.di.DispatcherModule.IoDispatcher
import br.com.grupo4.classoverflow.data.model.QuestionModel
import br.com.grupo4.classoverflow.data.repository.contract.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(
    private val questionRepository: QuestionRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _errorEvent = MutableLiveData<EventHandler<Boolean>>()
    val errorEvent: LiveData<EventHandler<Boolean>> get() = _errorEvent

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _questions = MutableLiveData<List<QuestionModel>>(listOf())
    val questions: LiveData<List<QuestionModel>> = _questions

    fun getQuestions() {
        viewModelScope.launch(dispatcher) {
            _isLoading.postValue(true)
            val response = questionRepository.getAll()
            if (response.success) {
                _questions.postValue(response.data ?: listOf())
            } else {
                _errorEvent.postValue(EventHandler(true))
            }

            _isLoading.postValue(false)
        }
    }
}