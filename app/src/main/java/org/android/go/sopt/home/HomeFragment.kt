package org.android.go.sopt.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import org.android.go.sopt.BottomAdapter
import org.android.go.sopt.MyAdapter
import org.android.go.sopt.TopAdapter
import org.android.go.sopt.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "앗 ! _binding이 null이다 !" }

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

    private fun initAdapter(){

        val topAdapter = TopAdapter(requireContext())
        val myAdapter = MyAdapter(requireContext())
        val bottomAdapter = BottomAdapter(requireContext())

        val concatAdapter = ConcatAdapter(topAdapter,myAdapter,bottomAdapter)

        with(binding.rv){
            adapter=concatAdapter
            layoutManager=LinearLayoutManager(context)
        }


    }



}