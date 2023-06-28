package org.android.go.sopt.present.menuFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import org.android.go.sopt.databinding.FragmentMyProfileBinding
import org.android.go.sopt.present.loginPage.LoginActivity
import org.android.go.sopt.present.viewModel.LoginPageViewModel
import org.android.go.sopt.util.MyApplication
import org.android.go.sopt.util.ViewModelFactory

class MyProfileFragment : Fragment() {
    private var _binding: FragmentMyProfileBinding? = null
    private val binding: FragmentMyProfileBinding
        get() = requireNotNull(_binding) { "앗 ! _binding이 null이다 !" }

    private val viewModel: LoginPageViewModel by viewModels { ViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    // 뷰가 만들어졌다.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.vmMyProfile = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        super.onViewCreated(view, savedInstanceState)
        getProfile()
        clickLogOut()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun getProfile() {
        viewModel.getMyProfile(MyApplication.mySharedPreferences.getUserId())
    }

    private fun observeMyProfile() {
        viewModel.getMyProfile.observe(this) {
            with(binding) {
                name.text = "이름 : ${it.data.name}"
                specialty.text = "특기 : ${it.data.skill}"
            }
        }
    }

    private fun clickLogOut() {
        binding.btnLogout.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Go SOPT").setMessage("로그아웃 하시겠나요?")
                .setPositiveButton("네") { _, _ ->
                    MyApplication.mySharedPreferences.clearUser()
                    val intent = Intent(activity, LoginActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }.setNegativeButton("아니요", null).setCancelable(true)
            builder.show()
        }
    }
}
