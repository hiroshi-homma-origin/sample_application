package com.example.timeline.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.timeline.R
import com.example.timeline.databinding.ItemTimeLineBinding
import com.example.timeline.ui.timeline.timeline.TimeLineFragmentDirections.actionTimeLineToDetail
import com.kotlin.project.data.model.TimeLineData

class TimeLineDataRecyclerViewAdapter(
    private val list: List<TimeLineData>,
    private val parentFragment: Fragment?,
    private val spanCount: Int
) : RecyclerView.Adapter<TimeLineDataRecyclerViewAdapter.TimeLineHolder>() {

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
                actionTimeLineToDetail(position)
            )
        }
        holder.binding.timeLine = list[position]
        holder.binding.isStatus = list[position].status == "typeB"
        holder.binding.imgWidth = when (spanCount) {
            3 -> 125
            else -> 150
        }
        holder.binding.imgHeight = when (spanCount) {
            3 -> 125
            else -> 150
        }
    }

    class TimeLineHolder(val binding: ItemTimeLineBinding) : RecyclerView.ViewHolder(binding.root)
}
