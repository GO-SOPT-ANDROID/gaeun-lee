package org.android.go.sopt.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.android.go.sopt.MultiViewAdapter
import org.android.go.sopt.data.DataObject
import org.android.go.sopt.data.MultiData
import org.android.go.sopt.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "앗 ! _binding이 null이다 !" }

    private val itemList =
        mutableListOf<MultiData>(
            MultiData(0, DataObject.TopRvTitle("노래 리스트")),
            MultiData(1, DataObject.Music("Kitsch", "IVE(아이브)")),
            MultiData(1, DataObject.Music("I AM", "IVE(아이브)")),
            MultiData(1, DataObject.Music("UNFORGIVEN", "LE SSEFAFIM(르세라핌)")),
            MultiData(1, DataObject.Music("꾳", "지수(JISOO)")),
            MultiData(1, DataObject.Music("손오공", "세븐틴(SEVENTEEN)")),
            MultiData(1, DataObject.Music("파이팅 해야지(Feat.이영지)", "부석순(SEVENTEEN)")),
            MultiData(1, DataObject.Music("Ditto", "NewJeans")),
            MultiData(1, DataObject.Music("Hype boy", "NewJeans")),
            MultiData(1, DataObject.Music("OMG", "NewJeans")),
            MultiData(1, DataObject.Music("사람 Pt.2(feat. 아이유)", "Agust D")),
            MultiData(1, DataObject.Music("FRIEND THE END", "볼빨간사춘기")),
            MultiData(1, DataObject.Music("물론", "허각")),
            MultiData(2, DataObject.BottomSponsor("주식회사 멜론")),
            MultiData(2, DataObject.BottomSponsor("후원사 SOPT"))
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {

        val multiAdapter = MultiViewAdapter(requireContext())
        multiAdapter.submitList(itemList)


        with(binding.rv) {
            adapter = multiAdapter
            layoutManager = LinearLayoutManager(context)
        }

    }

}