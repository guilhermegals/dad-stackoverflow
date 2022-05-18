package br.com.grupo4.classoverflow.feature.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.grupo4.classoverflow.data.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    fun getUser() {
        _user.value = User(
            name = "Guilherme Augusto",
            enrolment = "12345689"
        )
    }

    fun exit() {

    }

}