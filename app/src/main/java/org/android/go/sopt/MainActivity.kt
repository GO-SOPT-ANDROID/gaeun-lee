package org.android.go.sopt

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.android.go.sopt.databinding.ActivityMainBinding
import org.android.go.sopt.home.HomeFragment

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

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        if(currentFragment==null){
            supportFragmentManager.beginTransaction().add(R.id.fcv_main,HomeFragment()).commit()
        }





//        if (MySharedPreferences.getUserId(this).isNullOrBlank()
//            || MySharedPreferences.getUserPass(this).isNullOrBlank()
//        ) {
//            clickLogin()
//        }
//        clickSignup()


    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        this.currentFocus?.let { hideKeyboard(it) }
        return super.dispatchTouchEvent(ev)
    }


//    private fun clickLogin() {
//        binding.btnLogin.setOnClickListener {
//
//
//            if (id == binding.etId.text.toString() && password == binding.etPassword.text.toString()) {
//
//                MySharedPreferences.setUserId(this, binding.etId.text.toString())
//                MySharedPreferences.setUserPass(this, binding.etPassword.text.toString())
//
//                val intent = Intent(this, IntroduceActivity::class.java)
//
//                intent.putExtra("name", name)
//                intent.putExtra("speciality", speciality)
//                startActivity(intent)
//
//            }
//
//        }


//    }

//    private fun clickSignup() {
//
//        resultLauncher =
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//                if (result.resultCode == Activity.RESULT_OK) {
//                    id = result.data?.getStringExtra("id") ?: ""
//                    password = result.data?.getStringExtra("password") ?: ""
//                    name = result.data?.getStringExtra("name") ?: ""
//                    speciality = result.data?.getStringExtra("speciality") ?: ""
//
//
//                }
//            }
//        binding.btnSignup.setOnClickListener {
//            val intent = Intent(this, SignupActivity::class.java)
//            resultLauncher.launch(intent)
//        }
//    }

}