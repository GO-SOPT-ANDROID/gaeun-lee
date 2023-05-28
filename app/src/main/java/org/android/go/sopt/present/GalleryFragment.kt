package org.android.go.sopt.present

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import coil.load
import com.google.android.material.tabs.TabLayoutMediator
import org.android.go.sopt.R
import org.android.go.sopt.ViewPagerAdapter
import org.android.go.sopt.databinding.FragmentGalleryBinding
import org.android.go.sopt.util.ContentUriRequestBody

class GalleryFragment : Fragment() {
    private val viewModel by viewModels<GalleryViewModel>()
    private var _binding: FragmentGalleryBinding? = null
    private val binding: FragmentGalleryBinding
        get() = requireNotNull(_binding) { "앗 ! _binding이 null이다 !" }
    private val launcher =
        registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) { imageUriList: List<Uri> ->
            with(binding) {
                when (imageUriList.size) {
                    0 -> {
                        Toast.makeText(requireContext(), "이미지를 선택하지 않았습니다.", Toast.LENGTH_SHORT)
                            .show()
                    }

                    1 -> {
                        viewModel.setRequestBody(
                            ContentUriRequestBody(
                                requireContext(),
                                imageUriList[0]
                            )
                        )
                        ivGalleryFirst.load(imageUriList[0])
                        viewModel.uploadProfileImage()
                    }

                    2 -> {
                        ivGalleryFirst.load(imageUriList[0])
                        ivGallerySecond.load(imageUriList[1])
                    }

                    3 -> {
                        ivGalleryFirst.load(imageUriList[0])
                        ivGallerySecond.load(imageUriList[1])
                        ivGalleryThird.load(imageUriList[2])
                    }

                    else -> {
                        Toast.makeText(requireContext(), "3개까지의 이미지만 선택해주세요.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }

        }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(
                activity,
                " 허가",
                Toast.LENGTH_SHORT
            ).show()

        } else {
            Toast.makeText(
                activity,
                "불가",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


    override fun onCreateView( // 뷰를 만든다 << 이때 초기화하면 좋음
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { // 이제 반환하는 View가 Null일 수 없기 때문에, ?를 지워주셔도 됩니다.
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    // 뷰가 만들어졌다.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGalleryPickImage.setOnClickListener {
            launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }



        binding.root.setOnClickListener {
            requestPermissionLauncher.launch("android.permission.ACCESS_COARSE_LOCATION")
        }


    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}