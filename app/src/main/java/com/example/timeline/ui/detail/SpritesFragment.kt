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
import com.example.timeline.databinding.FragmentSpritesBinding
import com.example.timeline.ui.adapter.SpritesRecyclerViewAdapter
import timber.log.Timber
import javax.inject.Inject

class SpritesFragment @Inject constructor() : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val spritesViewModel: SpritesViewModel by viewModels { factory }
    private var _binding: FragmentSpritesBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val args: SpritesFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("check_args1:${args.number}")
        Timber.d("check_args2:${args.limit}")
        Timber.d("check_args3:${args.offset}")
        _binding = FragmentSpritesBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@SpritesFragment
            settingRecyclerView(recyclerView)
        }
        lifecycle.addObserver(spritesViewModel)
        if (!spritesViewModel.screenHasRotated) {
            observe()
            spritesViewModel.screenHasRotated = true
        }
        return binding.root
    }

    private fun observe() {
        spritesViewModel.pokeList(args.limit, args.offset).observe(viewLifecycleOwner) {
            if (spritesViewModel.pList.isEmpty() ||
                spritesViewModel.pList.size != it.size
            ) {
                spritesViewModel.pList = it
                binding.recyclerView.adapter =
                    SpritesRecyclerViewAdapter(
                        spritesViewModel,
                        parentFragment,
                        args.limit,
                        args.offset
                    )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            swipeRefresh.setOnRefreshListener {
                spritesViewModel.onRefresh(args.limit, args.offset)
                swipeRefresh.isRefreshing = false
            }
            recyclerView.adapter =
                SpritesRecyclerViewAdapter(
                    spritesViewModel,
                    parentFragment,
                    args.limit,
                    args.offset
                )
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
                    spritesViewModel.spanCount,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            gridLayoutManagerVertical.spanCount = spritesViewModel.spanCount
            recyclerView.layoutManager = gridLayoutManagerVertical
        } else {
            val gridLayoutManagerVertical =
                GridLayoutManager(
                    requireContext(),
                    spritesViewModel.spanCount * 2,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            gridLayoutManagerVertical.spanCount = spritesViewModel.spanCount * 2
            recyclerView.layoutManager = gridLayoutManagerVertical
        }
        // Challenge Imp (androidx.recyclerview:recyclerview:1.2.0-alpha06)
        recyclerView.adapter?.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
    }
}
