package br.com.grupo4.classoverflow.feature.questions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.grupo4.classoverflow.data.model.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor() : ViewModel() {

    private val _questions = MutableLiveData<List<Question>>(listOf())
    val questions: LiveData<List<Question>> = _questions

    fun getQuestions() {
        _questions.postValue(
            arrayListOf(
                Question(
                    title = "Título 1",
                    owner = "Usuário 1",
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.now(),
                    topic = listOf("#topico1", "#topico2"),
                    subject = "Materia1",
                    liked = true,
                    isActive = true
                ),
                Question(
                    title = "Título 2",
                    owner = "Usuário 1",
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.now(),
                    topic = listOf("#topico1"),
                    subject = "Materia2",
                    isActive = true
                ),
            )
        )
    }
}