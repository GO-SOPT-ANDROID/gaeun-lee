package org.android.go.sopt.present.loginPage

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.android.go.sopt.MainActivity
import org.android.go.sopt.data.model.RequestLogInDto
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.present.viewModel.LoginPageViewModel
import org.android.go.sopt.util.*

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginPageViewModel by viewModels { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (MyApplication.mySharedPreferences.getUserId().isNullOrBlank()
        ) {
            clickLogin()
            clickSignup()
            observeIsLoginSuccess()
        } else {
            alreadyLogin()
        }
    }

    private fun observeIsLoginSuccess() {
        viewModel.loginResult.observe(
            this,
            EventObserver { loginResult ->
                if (loginResult) {
                    makeToastMessage("로그인 성공")
                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                } else {
                    makeToastMessage("로그인 실패")
                }
            },
        )
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        this.currentFocus?.let { hideKeyboard(it) }
        with(binding) {
            etId.clearFocus()
            etPassword.clearFocus()
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun clickLogin() {
        binding.btnLogin.setOnClickListener {
            viewModel.login(
                RequestLogInDto(
                    binding.etId.text.toString(),
                    binding.etPassword.text.toString(),
                ),
            )
        }
    }

    private fun clickSignup() {
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun alreadyLogin() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }
}
