package org.android.go.sopt

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.android.go.sopt.databinding.ActivityIntroduceBinding
import org.android.go.sopt.databinding.ActivityMainBinding

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


    }
}