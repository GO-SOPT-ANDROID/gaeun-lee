package org.android.go.sopt.myPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
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

    private fun signUp(){
        binding.btnSignup.setOnClickListener {
            if (binding.etId.text.length in 6..10 && binding.etPassword.text.length in 8..12){
                val intent = Intent(this, LoginActivity::class.java)

                intent.putExtra("id",binding.etId.text.toString())
                intent.putExtra("password",binding.etPassword.text.toString())
                intent.putExtra("name",binding.etName.text.toString())
                intent.putExtra("speciality",binding.etSpeciality.text.toString())
                setResult(RESULT_OK,intent)
                finish()
                Snackbar.make(
                    binding.root,
                    "회원가입이 완료되었다.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            else{
                Snackbar.make(
                    binding.root,
                    "회원가입이 실패했다.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

        }
    }



}