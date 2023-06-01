package org.android.go.sopt.present.loginPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.MotionEvent
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.RequestSignUpDto
import org.android.go.sopt.ResponseSignUpDto
import org.android.go.sopt.databinding.ActivitySignupBinding
import org.android.go.sopt.remote.ServicePool
import org.android.go.sopt.util.hideKeyboard
import retrofit2.Call
import retrofit2.Response
import android.text.TextWatcher as TextWatcher


class SignUpActivity : AppCompatActivity() {

    private val signUpService = ServicePool.signUpService

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

                signUpService.login(
                    with(binding) {
                        RequestSignUpDto(
                            etId.text.toString(),
                            etPassword.text.toString(),
                            etName.text.toString(),
                            etSpeciality.text.toString()

                        )
                    }
                ).enqueue(object : retrofit2.Callback<ResponseSignUpDto> {
                    override fun onResponse(
                        call: Call<ResponseSignUpDto>,
                        response: Response<ResponseSignUpDto>
                    ) {
                        if (response.isSuccessful) {
                            response.body()?.message?.let {
                                Toast.makeText(
                                    this@SignUpActivity,
                                    response.body()?.message ?: "회원가입 성공",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                            setResult(RESULT_OK, intent)
                            finish()
                        } else {
                            Toast.makeText(this@SignUpActivity, "회원가입 실패", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                        Toast.makeText(this@SignUpActivity, "서버 통신 실패", Toast.LENGTH_SHORT).show()
                    }
                })

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