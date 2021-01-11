package com.example.timeline.ui.timeline.third

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timeline.databinding.FragmentThirdBinding
import com.example.timeline.ui.adapter.TimeLineDataRecyclerViewAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ThirdFragment : DaggerFragment() {

    companion object {
        private const val EXTRA_KEY_PATH = "path"
        fun newInstance(path: String) =
            ThirdFragment().apply {
                arguments = bundleOf(
                    EXTRA_KEY_PATH to path
                )
            }
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val thirdViewModel: ThirdViewModel by viewModels { factory }
    private var _binding: FragmentThirdBinding? = null
    private val binding get() = requireNotNull(_binding)
    private var path: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        path = arguments?.getString(EXTRA_KEY_PATH, "") ?: ""
        _binding = FragmentThirdBinding.inflate(inflater, container, false).apply {
            viewModel = this@ThirdFragment.thirdViewModel
            lifecycleOwner = viewLifecycleOwner
            settingRecyclerView(recyclerView)
        }
        lifecycle.addObserver(thirdViewModel)
        if (!thirdViewModel.screenHasRotated) {
            thirdViewModel.setPath(path)
            thirdViewModel.screenHasRotated = true
        }
        observe()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            this.swipeRefresh.setOnRefreshListener {
                thirdViewModel.onRefresh()
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
        thirdViewModel.list.observe(viewLifecycleOwner) { list ->
            binding.recyclerView.adapter = TimeLineDataRecyclerViewAdapter(
                list,
                parentFragment,
                thirdViewModel.spanCount,
                134,
                252
            )
        }
    }

    private fun settingRecyclerView(recyclerView: RecyclerView) {
        recyclerView.setHasFixedSize(true)
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val gridLayoutManagerVertical =
                GridLayoutManager(
                    requireContext(),
                    thirdViewModel.spanCount,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            gridLayoutManagerVertical.spanCount = thirdViewModel.spanCount
            recyclerView.layoutManager = gridLayoutManagerVertical
        } else {
            val gridLayoutManagerVertical =
                GridLayoutManager(
                    requireContext(),
                    thirdViewModel.spanCount * 2,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            gridLayoutManagerVertical.spanCount = thirdViewModel.spanCount * 2
            recyclerView.layoutManager = gridLayoutManagerVertical
        }
        // Challenge Imp (androidx.recyclerview:recyclerview:1.2.0-alpha06)
        recyclerView.adapter?.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }
}
