package br.com.grupo4.classoverflow.data.repository.implementation

import android.content.Context
import br.com.grupo4.classoverflow.data.model.UserModel
import com.auth0.android.jwt.JWT
import dagger.hilt.android.qualifiers.ApplicationContext

class SharedPreferencesRepository(
    @ApplicationContext val context: Context
) {

    companion object {
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
    }

    fun getUserInformation(): UserModel {
        val token = getAccessToken()
        val jwt = JWT(token)
        return UserModel(
            name = jwt.getClaim("unique_name").asString() ?: "",
            enrolment = jwt.getClaim("role").asString() ?: "",
            email = jwt.getClaim("email").asString() ?: ""
        )
    }

    fun getAccessToken(): String {
        return context.getSharedPreferences("SHARED_PREFERENCE", Context.MODE_PRIVATE)
            .getString(ACCESS_TOKEN, "") ?: ""
    }

    fun setAccessToken(value: String) {
        context.getSharedPreferences("SHARED_PREFERENCE", Context.MODE_PRIVATE).edit()
            .putString(ACCESS_TOKEN, value)
            .apply()
    }
}