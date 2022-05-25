package br.com.grupo4.classoverflow.feature.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.grupo4.classoverflow.core.EventHandler
import br.com.grupo4.classoverflow.core.di.DispatcherModule
import br.com.grupo4.classoverflow.data.model.LoginRequest
import br.com.grupo4.classoverflow.data.repository.contract.LoginRepository
import br.com.grupo4.classoverflow.data.repository.implementation.SharedPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository,
    @DispatcherModule.IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _openHomeEvent = MutableLiveData<EventHandler<Boolean>>()
    val openHomeEvent: LiveData<EventHandler<Boolean>> get() = _openHomeEvent

    private val _openRegisterEvent = MutableLiveData<EventHandler<Boolean>>()
    val openRegisterEvent: LiveData<EventHandler<Boolean>> get() = _openRegisterEvent

    private val _loginErrorEvent = MutableLiveData<EventHandler<String>>()
    val loginErrorEvent: LiveData<EventHandler<String>> get() = _loginErrorEvent

    val email: MutableLiveData<String> = MutableLiveData<String>()
    val password: MutableLiveData<String> = MutableLiveData<String>()

    fun checkLogin() {
        val token = sharedPreferencesRepository.getAccessToken()
        if (token.isNotEmpty()) _openHomeEvent.postValue(EventHandler(true))
    }

    fun login() {
        viewModelScope.launch(dispatcher) {
            _isLoading.postValue(true)

            val response = loginRepository.login(
                LoginRequest(
                    email = email.value ?: "",
                    password = password.value ?: ""
                )
            )

            if (response.success) _openHomeEvent.postValue(EventHandler(true))
            else _loginErrorEvent.postValue(EventHandler(response.message))

            _isLoading.postValue(false)
        }
    }

    fun register() {
        _openRegisterEvent.postValue(EventHandler(true))
    }
}