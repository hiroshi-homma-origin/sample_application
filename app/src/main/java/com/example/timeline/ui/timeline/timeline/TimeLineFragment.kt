package com.example.timeline.ui.timeline.timeline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.timeline.databinding.FragmentTimeLineBinding
import com.example.timeline.ui.adapter.TimeLineViewPagerAdapter
import com.example.timeline.ui.dialog.CheckMessageDialogDirections
import com.example.timeline.util.viewpager2.ZoomOutPageTransformer
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kotlin.project.data.model.Tab
import timber.log.Timber
import javax.inject.Inject

class TimeLineFragment @Inject constructor() : Fragment() {

    companion object {
        private const val EXTRA_KEY_CURRENT_NUMBER = "currentTabNumber"
        fun newInstance() = TimeLineFragment()
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: TimeLineViewModel by viewModels { factory }

    private var _binding: FragmentTimeLineBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val currentNumber =
            savedInstanceState?.getInt(EXTRA_KEY_CURRENT_NUMBER)
                ?: (viewModel.currentTabNumber.value ?: 1)
        _binding = FragmentTimeLineBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            settingViewPager(tab, viewPager, currentNumber)
            fab.setOnClickListener {
                val action = CheckMessageDialogDirections.actionGlobalCheckMessageDialog()
                findNavController().navigate(action)
            }
        }
        lifecycle.addObserver(viewModel)
        observe()
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.currentTabNumber.value?.let {
            outState.putInt(EXTRA_KEY_CURRENT_NUMBER, it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewModel.getPokeList().observe(viewLifecycleOwner) {
            Timber.d("check_observe_data:${it.size}")
        }
    }

    private fun settingViewPager(tab: TabLayout, viewPager: ViewPager2, currentNumber: Int) {
        viewPager.apply {
            adapter = TimeLineViewPagerAdapter(this@TimeLineFragment, factory)
            viewPager.setPageTransformer(ZoomOutPageTransformer())
            TabLayoutMediator(tab, viewPager) { t, p ->
                t.text = Tab.values()[p].displayName
            }.attach()
            setCurrentItem(currentNumber, false)
            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    viewModel.setCurrentTab(position)
                }
            })
        }
    }
}
