package com.example.timeline.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.timeline.R
import com.example.timeline.databinding.ItemTimeLineBinding
import com.example.timeline.ui.timeline.timeline.TimeLineFragmentDirections.actionTimeLineFragmentToDetailFragment
import com.kotlin.project.data.model.TimeLineData

class RecyclerViewAdapter(
    private val list: List<TimeLineData>,
    private val parentFragment: Fragment?
) : RecyclerView.Adapter<RecyclerViewAdapter.TimeLineHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineHolder {
        val binding = DataBindingUtil.inflate<ItemTimeLineBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_time_line,
            parent,
            false
        )
        return TimeLineHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TimeLineHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            parentFragment?.findNavController()?.navigate(
                actionTimeLineFragmentToDetailFragment(list[position].name)
            )
        }
        holder.binding.timeLine = list[position]
        holder.binding.isSoldOut = list[position].status == "sold_out"
        holder.binding.priceText = "$ ${list[position].price}"
    }

    class TimeLineHolder(val binding: ItemTimeLineBinding) : RecyclerView.ViewHolder(binding.root)
}
