package br.com.grupo4.classoverflow.feature.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.grupo4.classoverflow.core.EventHandler
import br.com.grupo4.classoverflow.data.model.UserModel
import br.com.grupo4.classoverflow.data.repository.implementation.SharedPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val sharedPreferencesRepository: SharedPreferencesRepository
) : ViewModel() {

    private val _logoutEvent = MutableLiveData<EventHandler<Boolean>>()
    val logoutEvent: LiveData<EventHandler<Boolean>> get() = _logoutEvent

    private val _user = MutableLiveData<UserModel>()
    val user: LiveData<UserModel> get() = _user

    fun getUser() {
        _user.postValue(sharedPreferencesRepository.getUserInformation())
    }

    fun exit() {
        sharedPreferencesRepository.setAccessToken("")
        _logoutEvent.postValue(EventHandler(true))
    }
}