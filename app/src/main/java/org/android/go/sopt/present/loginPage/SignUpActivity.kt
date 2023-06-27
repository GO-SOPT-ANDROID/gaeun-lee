package org.android.go.sopt.present.loginPage

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.MotionEvent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import org.android.go.sopt.MainActivity
import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.present.viewModel.LoginPageViewModel
import org.android.go.sopt.util.ViewModelFactory
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.util.makeToastMessage
import android.text.TextWatcher as TextWatcher

class SignUpActivity : AppCompatActivity() {

    private val viewModel: LoginPageViewModel by viewModels { ViewModelFactory() }

    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        canClickButton()
        binding.btnSignup.setOnClickListener {
            if (binding.etId.text.length in 6..10 && binding.etPassword.text.length in 8..12) {
                with(binding) {
                    viewModel.signUp(
                        RequestSignUpDto(
                            etId.text.toString(),
                            etPassword.text.toString(),
                            etName.text.toString(),
                            etSpeciality.text.toString(),
                        ),
                    )
                }
            } else {
                makeToastMessage("회원가입 조건 미충족")
            }
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

    private fun canClickButton() {
        with(binding) {
            btnSignup.isEnabled = false

            val textWatcher: TextWatcher = object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if (etId.text.length in 6..10 && etPassword.text.length in 8..12) {
                        btnSignup.isEnabled = true
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                }
            }

            etId.addTextChangedListener(textWatcher)
            etPassword.addTextChangedListener(textWatcher)
            etName.addTextChangedListener(textWatcher)
            etSpeciality.addTextChangedListener(textWatcher)
        }
    }
}
