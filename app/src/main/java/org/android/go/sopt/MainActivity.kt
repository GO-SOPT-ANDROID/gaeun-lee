package org.android.go.sopt

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import org.android.go.sopt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var id:String = ""
    private var password:String = ""
    private var name:String = ""
    private var mbti:String = ""

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if (result.resultCode == Activity.RESULT_OK){
                id = result.data?.getStringExtra("id") ?: ""
                password = result.data?.getStringExtra("password")?:""
                name= result.data?.getStringExtra("name")?:""
                mbti = result.data?.getStringExtra("mbti")?:""


            }
        }



        binding.loginButton.setOnClickListener{

            Log.d("MainActivity","아이디 : ${id}")
            Log.d("MainActivity","아이디 : ${binding.id.text}")

            if(id== binding.id.text.toString()){



                val intent = Intent(this,IntroduceActivity::class.java)

                intent.putExtra("name",name)
                intent.putExtra("mbti",mbti)
                startActivity(intent)
            }

        }

        binding.signupButton.setOnClickListener {
            val intent = Intent(this,SignupActivity::class.java)
            resultLauncher.launch(intent)
        }

    }





}