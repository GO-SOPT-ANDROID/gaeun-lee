package org.android.go.sopt


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.android.go.sopt.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.singupCompleteButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("id",binding.id.text.toString())
            intent.putExtra("password",binding.password.text.toString())
            intent.putExtra("name",binding.name.text.toString())
            intent.putExtra("mbti",binding.mbti.text.toString())
            setResult(RESULT_OK,intent)
            finish()


        }



    }


}