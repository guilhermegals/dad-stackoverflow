package br.com.grupo4.classoverflow.feature.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.grupo4.classoverflow.core.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuestionDetailViewModel @Inject constructor() : ViewModel() {

    private val _backEvent = MutableLiveData<EventHandler<Boolean>>()
    val backEvent: LiveData<EventHandler<Boolean>> get() = _backEvent

    fun back(){
        _backEvent.postValue(EventHandler(true))
    }
}