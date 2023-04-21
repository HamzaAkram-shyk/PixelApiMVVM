package com.example.myapplication.presenter.ui.gridview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMainBinding
import com.example.myapplication.presenter.ui.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GridViewFragment : Fragment() {

    private val viewModel by viewModels<GirdViewViewModel>()
    private lateinit var binding: FragmentMainBinding
    private lateinit var gridViewAdapter: GridViewAdapter

    companion object {
        fun newInstance() = GridViewFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPixelData()
        initAdapter()
        viewModel.uiStateEvents.observe(viewLifecycleOwner) {
            when (val event = it.getContentIfNotHandled()) {
                is GridViewEvent.CollectList -> gridViewAdapter.addItems(event.list)
                is GridViewEvent.Failure -> {
                    Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                }
                else -> {}
            }
        }
    }

    private fun initAdapter() {
        gridViewAdapter = GridViewAdapter { item ->
            requireActivity().supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, DetailFragment.newInstance(item.largeImageURL))
                .addToBackStack(DetailFragment::class.java.name)
                .commitAllowingStateLoss()
        }
        binding.mainRecyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = gridViewAdapter
        }
    }
}