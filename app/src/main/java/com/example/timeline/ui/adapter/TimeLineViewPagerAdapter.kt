package com.example.timeline.ui.adapter

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.timeline.ui.timeline.all.AllFragment
import com.example.timeline.ui.timeline.dataA.DataAFragment
import com.example.timeline.ui.timeline.dataB.DataBFragment
import com.kotlin.project.data.model.Tab

class TimeLineViewPagerAdapter(
    fragment: Fragment,
    private val factory: ViewModelProvider.Factory
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = Tab.values().size

    override fun createFragment(position: Int): Fragment {
        return when (Tab.values()[position]) {
            Tab.DATA_A -> DataAFragment.newInstance(factory, Tab.DATA_A.path)
            Tab.DATA_B -> DataBFragment.newInstance(factory, Tab.DATA_B.path)
            Tab.ALL -> AllFragment.newInstance(factory, Tab.ALL.path)
        }
    }
}
