package com.example.timeline.ui.adapter

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.timeline.ui.timeline.first.FirstFragment
import com.example.timeline.ui.timeline.second.SecondFragment
import com.example.timeline.ui.timeline.fourth.FourthFragment
import com.example.timeline.ui.timeline.third.ThirdFragment
import com.kotlin.project.data.model.Tab

class TimeLineViewPagerAdapter(
    fragment: Fragment,
    private val factory: ViewModelProvider.Factory
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = Tab.values().size

    override fun createFragment(position: Int): Fragment {
        return when (Tab.values()[position]) {
            Tab.FIRST -> FirstFragment.newInstance(factory, Tab.FIRST.path)
            Tab.SECOND -> SecondFragment.newInstance(factory, Tab.SECOND.path)
            Tab.THIRD -> ThirdFragment.newInstance(factory, Tab.THIRD.path)
            Tab.FOURTH -> FourthFragment.newInstance(factory, Tab.FOURTH.path)
        }
    }
}
