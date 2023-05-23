package org.android.go.sopt.myPage

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
import org.android.go.sopt.databinding.ActivityLoginBinding
import org.android.go.sopt.remote.ServicePool
import org.android.go.sopt.remote.model.RequestLogInDto
import org.android.go.sopt.remote.model.ResponseLogInDto
import org.android.go.sopt.remote.service.LogInService
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.viewModel.LoginViewModel
import retrofit2.Call
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    private lateinit var id: String
    private lateinit var password: String
    private lateinit var name: String
    private lateinit var speciality: String

    private val logInService = ServicePool.logInService

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
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
            val intent = Intent(this@LoginActivity, IntroduceActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK)

            loginResult.data?.let {
                MySharedPreferences.setUserId(this@LoginActivity, it.id)
                }


            startActivity(intent)
            finish()

            loginResult.message?.let {
                Toast.makeText(
                    this@LoginActivity,
                    loginResult.message ?: "로그인 성공",
                    Toast.LENGTH_SHORT
                ).show()

            }


        }


    }


    private fun clickSignup() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    id = result.data?.getStringExtra("id") ?: ""
                    password = result.data?.getStringExtra("password") ?: ""
                    name = result.data?.getStringExtra("name") ?: ""
                    speciality = result.data?.getStringExtra("speciality") ?: ""


                }
            }
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun alreadyLogin() {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK)
        val intent = Intent(this, IntroduceActivity::class.java)
        intent.putExtra("name", MySharedPreferences.getUserName(this))
        intent.putExtra("speciality", MySharedPreferences.getUserSpec(this))
        startActivity(intent)
        finish()
    }


}