package org.android.go.sopt.present.loginPage

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.present.viewModel.LoginPageViewModel
import org.android.go.sopt.util.ViewModelFactory
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.util.makeToastMessage

class SignUpActivity : AppCompatActivity() {

    private val viewModel: LoginPageViewModel by viewModels { ViewModelFactory() }

    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            vm = viewModel
            lifecycleOwner = this@SignUpActivity
        }

        signUp()
        observeIsSignUpSuccess()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        this.currentFocus?.let { hideKeyboard(it) }
        with(binding) {
            etId.clearFocus()
            etPassword.clearFocus()
            etName.clearFocus()
            etSpeciality.clearFocus()
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun signUp() {
        binding.btnSignup.setOnClickListener {
            viewModel.signUp(
                with(binding) {
                    RequestSignUpDto(
                        etId.text.toString(),
                        etPassword.text.toString(),
                        etName.text.toString(),
                        etSpeciality.text.toString(),
                    )
                },
            )
        }
    }

    private fun observeIsSignUpSuccess() {
        viewModel.signUpResult.observe(this) { signUpResult ->
            if (signUpResult) {
                makeToastMessage("회원가입 성공")
                val intent = Intent(this, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            } else {
                makeToastMessage("회원가입 실패")
            }
        }
    }
}
