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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import org.android.go.sopt.databinding.ActivityMainBinding
import org.android.go.sopt.home.GalleryFragment
import org.android.go.sopt.home.HomeFragment
import org.android.go.sopt.home.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        if (currentFragment == null) {
            supportFragmentManager.beginTransaction().add(R.id.fcv_main, HomeFragment()).commit()
        }


        binding.bnvMain.setOnItemSelectedListener { item ->
            // 선택된 탭의 itemId와 현재 보여지고 있는 FragmentContainerView의 itemId가 같은 경우
            // 즉 같은 탭을 다시 선택한 경우에 scrollTo 메소드를 호출
            if (binding.bnvMain.selectedItemId == item.itemId){
                binding.fcvMain.scrollTo(0,0)
            }

            when (item.itemId) {
                R.id.menu_home -> {
                    changeFragment(HomeFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.menu_search -> {
                    changeFragment(SearchFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.menu_gallery -> {
                    changeFragment(GalleryFragment())
                    return@setOnItemSelectedListener true
                }
                else -> {
                    false
                }

            }


        }

    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()

    }


}