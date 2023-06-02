package org.android.go.sopt.present.loginPage

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import org.android.go.sopt.MainActivity
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.remote.ServicePool
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.present.viewModel.LoginViewModel
import org.android.go.sopt.util.makeToastMessage

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (MySharedPreferences.getUserId(this).isNullOrBlank()
        ) {
            clickLogin()
            clickSignup()
        } else {
            alreadyLogin()
        }


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
                binding.etId.text.toString(),
                binding.etPassword.text.toString()
            )
        }


        viewModel.loginResult.observe(this) { loginResult ->
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK)

            loginResult.data?.let {
                MySharedPreferences.setUserId(this@LoginActivity, it.id)
                }


            startActivity(intent)
            finish()

            loginResult.message?.let {
                makeToastMessage("로그인 성공")
            }

        }


    }


    private fun clickSignup() {
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    private fun alreadyLogin() {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK)
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("name", MySharedPreferences.getUserName(this))
        intent.putExtra("speciality", MySharedPreferences.getUserSpec(this))
        startActivity(intent)
        finish()
    }


}