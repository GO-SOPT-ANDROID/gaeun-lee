package org.android.go.sopt.myPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.android.go.sopt.databinding.ActivityIntroduceBinding

class IntroduceActivity : AppCompatActivity() {
    lateinit var binding: ActivityIntroduceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroduceBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var name = intent.getStringExtra("name") ?: ""
        var speciality = intent.getStringExtra("speciality") ?: ""

        binding.name.text = "이름 : $name"
        binding.specialty.text = "특기 : $speciality"

        binding.btnLogout.setOnClickListener {
            MySharedPreferences.clearUser(this)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}