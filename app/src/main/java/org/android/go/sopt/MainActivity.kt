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
                    return@setOnItemSelectedListener false
                }

            }


        }

        binding.bnvMain.setOnItemReselectedListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)

            if (currentFragment is HomeFragment) {
                val scrollView = currentFragment.view?.findViewById<RecyclerView>(R.id.rv)
                scrollView?.scrollToPosition(0)
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