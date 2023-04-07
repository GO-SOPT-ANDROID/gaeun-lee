package org.android.go.sopt


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSignup.setOnClickListener {
            if (binding.etId.text.length in 6..10 && binding.etPassword.text.length in 8..12){
                val intent = Intent(this, MainActivity::class.java)

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

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        this.currentFocus?.let { hideKeyboard(it) }
        return super.dispatchTouchEvent(ev)
    }


}