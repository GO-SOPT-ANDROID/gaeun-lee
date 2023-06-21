package org.android.go.sopt.present.loginPage

import android.content.Intent
import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.MainActivity
import org.android.go.sopt.R
import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.ResponseSignUpDto
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.present.viewModel.LoginViewModel
import org.android.go.sopt.remote.ServicePool
import org.android.go.sopt.util.hideKeyboard
import org.android.go.sopt.util.makeToastMessage
import retrofit2.Call
import retrofit2.Response
import java.util.regex.Pattern
import android.text.TextWatcher as TextWatcher


class SignUpActivity : AppCompatActivity() {

    private val viewModel by viewModels<LoginViewModel>()

    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUp()
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
                        etId.text.toString(),
                        etPassword.text.toString(),
                        etName.text.toString(),
                        etSpeciality.text.toString()
                    )
                }

                viewModel.signUpResult.observe(this) {
                    makeToastMessage("회원가입 성공")
                    val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                }

            } else {
                Snackbar.make(
                    binding.root,
                    "회원가입이 실패했다.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

        }
    }

    private fun canClickButton() {

        var correctId: Boolean = false
        var correctPw: Boolean = false

        with(binding) {
            btnSignup.isEnabled = false

            etId.addTextChangedListener(object : TextWatcher {

                val idPattern = "^(?=.*\\d)(?=.*[a-zA-Z]).{6,10}\$"


                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                    correctId = Pattern.matches(idPattern, etId.text)
                    if (!correctId) {
                        tvIdWarn.visibility = View.VISIBLE
                        correctId = false
                        etId.backgroundTintList = ColorStateList.valueOf(getColor(R.color.red_500))
                    } else {
                        tvIdWarn.visibility = View.GONE
                        correctId = true
                        etId.backgroundTintList = ColorStateList.valueOf(getColor(R.color.black))
                    }

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (correctId && correctPw) {
                        btnSignup.isEnabled = true
                    }
                }

            })
            etPassword.addTextChangedListener(object : TextWatcher {

                val pwPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&]).{8,15}.\$"



                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    correctPw = Pattern.matches(pwPattern, etPassword.text)
                    if (!correctPw) {
                        tvPwWarn.visibility = View.VISIBLE
                        correctPw = false
                        etPassword.backgroundTintList =
                            ColorStateList.valueOf(getColor(R.color.red_500))
                    } else {
                        tvPwWarn.visibility = View.GONE
                        correctPw = true
                        etPassword.backgroundTintList =
                            ColorStateList.valueOf(getColor(R.color.black))
                    }
                }

                override fun afterTextChanged(p0: Editable?) {
                    if (correctId && correctPw) {
                        btnSignup.isEnabled = true
                    }
                }

            })


        }

    }


}