package com.example.timeline.ui.detail

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.timeline.databinding.FragmentDetailBinding
import com.example.timeline.ui.adapter.DetailRecyclerViewAdapter
import timber.log.Timber
import javax.inject.Inject

class DetailFragment @Inject constructor() : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val detailViewModel: DetailViewModel by viewModels { factory }
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@DetailFragment
            settingRecyclerView(recyclerView)
        }
        lifecycle.addObserver(detailViewModel)
        observe()
        return binding.root
    }

    private fun observe() {
        detailViewModel.pokeList().observe(viewLifecycleOwner) {
            Timber.d("check_test1:${it.size}")
            binding.recyclerView.adapter = DetailRecyclerViewAdapter(it, parentFragment, detailViewModel.spanCount)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            swipeRefresh.setOnRefreshListener {
                detailViewModel.onRefresh()
                swipeRefresh.isRefreshing = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun settingRecyclerView(recyclerView: RecyclerView) {
        recyclerView.setHasFixedSize(true)
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val gridLayoutManagerVertical =
                GridLayoutManager(
                    requireContext(),
                    detailViewModel.spanCount,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            gridLayoutManagerVertical.spanCount = detailViewModel.spanCount
            recyclerView.layoutManager = gridLayoutManagerVertical
        } else {
            val gridLayoutManagerVertical =
                GridLayoutManager(
                    requireContext(),
                    detailViewModel.spanCount * 2,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            gridLayoutManagerVertical.spanCount = detailViewModel.spanCount * 2
            recyclerView.layoutManager = gridLayoutManagerVertical
        }
        // Challenge Imp (androidx.recyclerview:recyclerview:1.2.0-alpha06)
        recyclerView.adapter?.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }
}
