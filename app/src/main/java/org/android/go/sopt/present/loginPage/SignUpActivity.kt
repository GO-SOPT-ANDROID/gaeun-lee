package org.android.go.sopt.present.loginPage

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import org.android.go.sopt.R
import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.present.viewModel.LoginPageViewModel
import org.android.go.sopt.util.ViewModelFactory
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.util.makeToastMessage
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    private val viewModel: LoginPageViewModel by viewModels { ViewModelFactory() }

    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            vmSignUp = viewModel
            lifecycleOwner = this@SignUpActivity
            etId.doAfterTextChanged { checkValidSignUpId() }
            etPassword.doAfterTextChanged { checkValidSignUpPwd() }
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

    private fun checkValidSignUpId(): Boolean {
        var correctId = false

        val idPattern = "^(?=.*\\d)(?=.*[a-zA-Z]).{6,10}\$"

        viewModel.signUpId.observe(this) { id ->
            correctId = Pattern.matches(idPattern, id)
            if (!correctId) {
                binding.tvIdWarn.visibility = View.VISIBLE
                correctId = false
                binding.etId.backgroundTintList = ColorStateList.valueOf(getColor(R.color.red_500))
            } else {
                binding.tvIdWarn.visibility = View.GONE
                correctId = true
                binding.etId.backgroundTintList = ColorStateList.valueOf(getColor(R.color.black))
            }
        }

        return correctId
    }

    private fun checkValidSignUpPwd(): Boolean {
        var correctPw = false

        val pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&]).{6,12}\$"

        viewModel.signUpPwd.observe(this) { pwd ->
            correctPw = Pattern.matches(pwPattern, pwd)
            if (!correctPw) {
                binding.tvPwWarn.visibility = View.VISIBLE
                correctPw = false
                binding.etPassword.backgroundTintList =
                    ColorStateList.valueOf(getColor(R.color.red_500))
            } else {
                binding.tvPwWarn.visibility = View.GONE
                correctPw = true
                binding.etPassword.backgroundTintList =
                    ColorStateList.valueOf(getColor(R.color.black))
            }
        }

        return correctPw
    }
}
