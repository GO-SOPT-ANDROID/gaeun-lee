package org.android.go.sopt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.android.go.sopt.databinding.ActivityMainBinding
import org.android.go.sopt.present.menuFragment.GalleryFragment
import org.android.go.sopt.present.menuFragment.HomeFragment
import org.android.go.sopt.present.menuFragment.MyProfileFragment
import org.android.go.sopt.present.menuFragment.SearchFragment

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
            if (binding.bnvMain.selectedItemId == item.itemId) {
                binding.fcvMain.scrollTo(0, 0)
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
                R.id.menu_profile -> {
                    changeFragment(MyProfileFragment())
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
