package com.example.timeline.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.timeline.R
import com.example.timeline.databinding.ItemSpritesItemBinding
import com.example.timeline.ui.sprites.SpritesFragmentDirections.actionSpritesToSprites
import com.example.timeline.ui.sprites.SpritesViewModel

class SpritesRecyclerViewAdapter(
    private val spritesViewModel: SpritesViewModel,
    private val parentFragment: Fragment?,
    private val limit: Int,
    private val offset: Int
) : RecyclerView.Adapter<SpritesRecyclerViewAdapter.TimeLineHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeLineHolder {
        val binding = DataBindingUtil.inflate<ItemSpritesItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_sprites_item,
            parent,
            false
        )
        return TimeLineHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return spritesViewModel.pList.size
    }

    override fun onBindViewHolder(holder: TimeLineHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            parentFragment?.findNavController()?.navigate(
                actionSpritesToSprites(position, limit, offset)
            )
        }
        holder.binding.result = spritesViewModel.pList[position]
        holder.binding.imgWidth = when (spritesViewModel.spanCount) {
            3 -> 125
            else -> 150
        }
        holder.binding.imgHeight = when (spritesViewModel.spanCount) {
            3 -> 125
            else -> 150
        }
    }

    class TimeLineHolder(val binding: ItemSpritesItemBinding) : RecyclerView.ViewHolder(binding.root)
}
