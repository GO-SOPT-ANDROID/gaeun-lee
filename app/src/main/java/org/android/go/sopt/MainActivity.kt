package org.android.go.sopt

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.android.go.sopt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var id: String
    private lateinit var password: String
    private lateinit var name: String
    private lateinit var speciality: String

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (MySharedPreferences.getUserId(this).isNullOrBlank()
            || MySharedPreferences.getUserPass(this).isNullOrBlank()
        ) {
            clickLogin()
            clickSignup()
        } else {
            alreadyLogin()
        }


    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        this.currentFocus?.let { hideKeyboard(it) }
        return super.dispatchTouchEvent(ev)
    }


    private fun clickLogin() {
        binding.btnLogin.setOnClickListener {


            if (id == binding.etId.text.toString() && password == binding.etPassword.text.toString()) {


                val intent = Intent(this, IntroduceActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK)

                MySharedPreferences.setUserId(this, id)
                MySharedPreferences.setUserPass(this, password)
                MySharedPreferences.setUserName(this, name)
                MySharedPreferences.setUserSpec(this, speciality)

                intent.putExtra("name", name)
                intent.putExtra("speciality", speciality)
                startActivity(intent)
                finish()

            }

        }
    }

    private fun clickSignup() {
        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    id = result.data?.getStringExtra("id") ?: ""
                    password = result.data?.getStringExtra("password") ?: ""
                    name = result.data?.getStringExtra("name") ?: ""
                    speciality = result.data?.getStringExtra("speciality") ?: ""


                }
            }
        binding.btnSignup.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }
    }

    private fun alreadyLogin() {
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK)
        val intent = Intent(this, IntroduceActivity::class.java)
        intent.putExtra("name", MySharedPreferences.getUserName(this))
        intent.putExtra("speciality", MySharedPreferences.getUserSpec(this))
        startActivity(intent)
        finish()
    }


}