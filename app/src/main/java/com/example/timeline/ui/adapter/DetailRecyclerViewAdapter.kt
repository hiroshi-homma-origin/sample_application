package com.example.timeline.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.timeline.R
import com.example.timeline.databinding.ItemDetailItemBinding
import com.example.timeline.ui.detail.DetailFragmentDirections.actionDetailToDetail
import com.kotlin.project.data.model.Results

class DetailRecyclerViewAdapter(
    private val results: List<Results>,
    private val parentFragment: Fragment?,
    private val spanCount: Int
) : RecyclerView.Adapter<DetailRecyclerViewAdapter.TimeLineHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineHolder {
        val binding = DataBindingUtil.inflate<ItemDetailItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_detail_item,
            parent,
            false
        )
        return TimeLineHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return results.size
    }

    override fun onBindViewHolder(holder: TimeLineHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            parentFragment?.findNavController()?.navigate(
                actionDetailToDetail(position)
            )
        }
        holder.binding.result = results[position]
        holder.binding.imgWidth = when (spanCount) {
            3 -> 125
            else -> 150
        }
        holder.binding.imgHeight = when (spanCount) {
            3 -> 125
            else -> 150
        }
    }

    class TimeLineHolder(val binding: ItemDetailItemBinding) : RecyclerView.ViewHolder(binding.root)
}
