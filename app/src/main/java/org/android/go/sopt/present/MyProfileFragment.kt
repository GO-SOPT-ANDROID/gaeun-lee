package org.android.go.sopt.present

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import org.android.go.sopt.R
import org.android.go.sopt.ViewPagerAdapter
import org.android.go.sopt.databinding.FragmentGalleryBinding
import org.android.go.sopt.databinding.FragmentMyProfileBinding

class MyProfileFragment : Fragment() {
    private var _binding: FragmentMyProfileBinding? = null
    private val binding: FragmentMyProfileBinding
        get() = requireNotNull(_binding) { "앗 ! _binding이 null이다 !" }

    override fun onCreateView( // 뷰를 만든다 << 이때 초기화하면 좋음
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { // 이제 반환하는 View가 Null일 수 없기 때문에, ?를 지워주셔도 됩니다.
        _binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    // 뷰가 만들어졌다.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}