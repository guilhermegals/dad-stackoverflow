package br.com.grupo4.classoverflow.feature.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.grupo4.classoverflow.data.repository.implementation.SharedPreferencesRepository
import br.com.grupo4.classoverflow.feature.HomeActivity
import br.com.grupo4.classoverflow.feature.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferencesRepository: SharedPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val access = sharedPreferencesRepository.getAccessToken()
        if (access.isBlank()) startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        else startActivity(Intent(this@SplashActivity, HomeActivity::class.java))

        finish()
    }
}