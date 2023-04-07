package org.android.go.sopt


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.root.setOnClickListener {
            hidKeyboard()
        }

        binding.singupCompleteButton.setOnClickListener {
            if (binding.id.text.length in 6..10 && binding.password.text.length in 8..12){
                val intent = Intent(this, MainActivity::class.java)

                intent.putExtra("id",binding.id.text.toString())
                intent.putExtra("password",binding.password.text.toString())
                intent.putExtra("name",binding.name.text.toString())
                intent.putExtra("speciality",binding.mbti.text.toString())
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

    private fun hidKeyboard() {
        val imm: InputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

    }



}