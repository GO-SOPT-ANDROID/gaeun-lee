package org.android.go.sopt.myPage

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
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
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Go SOPT").setMessage("로그아웃 하시겠나요?")
                .setPositiveButton("네") { _, _ ->
                    MySharedPreferences.clearUser(this)
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()

                }.setNegativeButton("아니요", null).setCancelable(true)
            builder.show()

        }

    }
}