package org.android.go.sopt.present.menuFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StableIdKeyProvider
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import org.android.go.sopt.MultiViewAdapter
import org.android.go.sopt.data.model.ResponseListUsersDto
import org.android.go.sopt.databinding.FragmentHomeBinding
import org.android.go.sopt.present.viewModel.MainPageViewModel
import org.android.go.sopt.util.ViewModelFactory

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = requireNotNull(_binding) { "앗 ! _binding이 null이다 !" }

    private val viewModel: MainPageViewModel by viewModels { ViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUserList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter(itemList: List<ResponseListUsersDto.Data>?) {
        val multiAdapter = MultiViewAdapter(requireContext())
        multiAdapter.submitList(itemList)

        with(binding.rv) {
            adapter = multiAdapter
            layoutManager = LinearLayoutManager(context)
        }

        val itemSelectionTracker = SelectionTracker.Builder<Long>(
            "todo-content",
            binding.rv,
            StableIdKeyProvider(binding.rv),
            MultiViewAdapter.MyItemDetailsLookup(binding.rv),
            StorageStrategy.createLongStorage(),
        ).withSelectionPredicate(SelectionPredicates.createSelectAnything()).build()
        multiAdapter.setSelectionTracker(itemSelectionTracker)
    }

    private fun getUserList() {
        viewModel.getUserList()
        viewModel.userList.observe(this) { response ->
            initAdapter(response)
        }
    }
}
