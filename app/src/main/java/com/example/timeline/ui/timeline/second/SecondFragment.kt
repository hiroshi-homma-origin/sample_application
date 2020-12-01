package com.example.timeline.ui.timeline.second

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
import com.example.timeline.databinding.FragmentSecondBinding
import com.example.timeline.ui.adapter.TimeLineDataRecyclerViewAdapter
import javax.inject.Inject

class SecondFragment @Inject constructor(
    private val factory: ViewModelProvider.Factory
) : Fragment() {

    companion object {
        private const val EXTRA_KEY_PATH = "path"
        fun newInstance(factory: ViewModelProvider.Factory, path: String) =
            SecondFragment(factory).apply {
                arguments = bundleOf(
                    EXTRA_KEY_PATH to path
                )
            }
    }

    private val secondViewModel: SecondViewModel by viewModels { factory }
    private var _binding: FragmentSecondBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var path: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        path = arguments?.getString(EXTRA_KEY_PATH, "") ?: ""
        _binding = FragmentSecondBinding.inflate(inflater, container, false).apply {
            viewModel = this@SecondFragment.secondViewModel
            lifecycleOwner = viewLifecycleOwner
            settingRecyclerView(recyclerView)
        }
        lifecycle.addObserver(secondViewModel)
        if (!secondViewModel.screenHasRotated) {
            secondViewModel.setPath(path)
            secondViewModel.screenHasRotated = true
        }
        observe()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            this.swipeRefresh.setOnRefreshListener {
                secondViewModel.onRefresh()
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
        secondViewModel.list.observe(viewLifecycleOwner) { list ->
            binding.recyclerView.adapter = TimeLineDataRecyclerViewAdapter(
                list,
                parentFragment,
                secondViewModel.spanCount,
                99,
                152
            )
        }
    }

    private fun settingRecyclerView(recyclerView: RecyclerView) {
        recyclerView.setHasFixedSize(true)
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val gridLayoutManagerVertical =
                GridLayoutManager(
                    requireContext(),
                    secondViewModel.spanCount,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            gridLayoutManagerVertical.spanCount = secondViewModel.spanCount
            recyclerView.layoutManager = gridLayoutManagerVertical
        } else {
            val gridLayoutManagerVertical =
                GridLayoutManager(
                    requireContext(),
                    secondViewModel.spanCount * 2,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            gridLayoutManagerVertical.spanCount = secondViewModel.spanCount * 2
            recyclerView.layoutManager = gridLayoutManagerVertical
        }
        // Challenge Imp (androidx.recyclerview:recyclerview:1.2.0-alpha06)
        recyclerView.adapter?.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }
}
