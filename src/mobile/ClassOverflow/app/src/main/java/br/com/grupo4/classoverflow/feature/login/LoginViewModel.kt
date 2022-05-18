package br.com.grupo4.classoverflow.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.grupo4.classoverflow.core.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _openHomeEvent = MutableLiveData<EventHandler<Boolean>>()
    val openHomeEvent: LiveData<EventHandler<Boolean>> get() = _openHomeEvent

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    val credential: MutableLiveData<String> = MutableLiveData<String>()
    val password: MutableLiveData<String> = MutableLiveData<String>()

    fun login() {
        _openHomeEvent.postValue(EventHandler(true))
    }
}