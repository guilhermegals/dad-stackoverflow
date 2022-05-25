package br.com.grupo4.classoverflow.feature.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.grupo4.classoverflow.core.EventObserver
import br.com.grupo4.classoverflow.core.Utils
import br.com.grupo4.classoverflow.core.goneUnless
import br.com.grupo4.classoverflow.databinding.ActivityLoginBinding
import br.com.grupo4.classoverflow.feature.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    // region [ Properties ]

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    // endregion

    // region [ Actions ]

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setContentView(binding.root)

        setupViews()
        setupObservers()

        viewModel.checkLogin()
    }

    // endregion

    // region [ Private Functions ]

    private fun setupViews() {
    }

    private fun setupObservers() {
        viewModel.openRegisterEvent.observe(this, EventObserver {
            if (it) openRegister()
        })

        viewModel.openHomeEvent.observe(this, EventObserver { result ->
            if (result) openHome()
        })

        viewModel.loginErrorEvent.observe(this, EventObserver { message ->
            if (message.isNotEmpty()) {
                binding.loginMessage.goneUnless(true)
                binding.loginMessage.text = message
            }
        })
    }

    private fun openRegister() {
        Utils.vibrate(this@LoginActivity)

        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("http://nevesg-001-site1.dtempurl.com/")
        }
        startActivity(intent)
    }

    private fun openHome() {
        Utils.vibrate(this@LoginActivity)

        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    // endregion
}