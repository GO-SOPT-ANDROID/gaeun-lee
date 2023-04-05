package org.android.go.sopt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import org.android.go.sopt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener{
            Snackbar.make(
                binding.root,
                "안녕하세요",
                Snackbar.LENGTH_LONG
            ).show()

        }

        binding.signupButton.setOnClickListener {
            val Intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }







    }
}