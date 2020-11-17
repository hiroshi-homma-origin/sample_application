package com.example.timeline.ui.timeline.dataA

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timeline.databinding.FragmentDataABinding
import com.example.timeline.ui.adapter.RecyclerViewAdapter
import javax.inject.Inject

class DataAFragment @Inject constructor(
    private val factory: ViewModelProvider.Factory
) : Fragment() {

    companion object {
        private const val EXTRA_KEY_PATH = "path"
        fun newInstance(factory: ViewModelProvider.Factory, path: String) =
            DataAFragment(factory).apply {
                arguments = bundleOf(
                    EXTRA_KEY_PATH to path
                )
            }
    }

    private val dataAViewModel: DataAViewModel by viewModels { factory }
    private var _binding: FragmentDataABinding? = null
    private val binding get() = requireNotNull(_binding)
    private var path: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        path = arguments?.getString(EXTRA_KEY_PATH, "") ?: ""
        _binding = FragmentDataABinding.inflate(inflater, container, false).apply {
            viewModel = this@DataAFragment.dataAViewModel
            lifecycleOwner = this@DataAFragment
            settingRecyclerView(recyclerView)
        }
        lifecycle.addObserver(dataAViewModel)
        if (!dataAViewModel.screenHasRotated) {
            dataAViewModel.setPath(path)
            dataAViewModel.screenHasRotated = true
        }
        observe()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            this.swipeRefresh.setOnRefreshListener {
                dataAViewModel.onRefresh()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_KEY_PATH, path)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        dataAViewModel.list.observe(viewLifecycleOwner) { list ->
            binding.recyclerView.adapter = RecyclerViewAdapter(list, parentFragment)
        }
    }

    private fun settingRecyclerView(recyclerView: RecyclerView) {
        recyclerView.setHasFixedSize(true)
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val gridLayoutManagerVertical =
                GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
            gridLayoutManagerVertical.spanCount = 2
            recyclerView.layoutManager = gridLayoutManagerVertical
        } else {
            val gridLayoutManagerVertical =
                GridLayoutManager(requireContext(), 4, LinearLayoutManager.VERTICAL, false)
            gridLayoutManagerVertical.spanCount = 4
            recyclerView.layoutManager = gridLayoutManagerVertical
        }
        // Challenge Imp (androidx.recyclerview:recyclerview:1.2.0-alpha06)
        recyclerView.adapter?.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }
}
