package com.example.wb_7_3.presentation.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wb_7_3.R
import com.example.wb_7_3.databinding.FavoriteRvItemBinding
import com.example.wb_7_3.domain.model.CatModelDomain

class FavoriteAdapter : ListAdapter<CatModelDomain, FavoriteAdapter.MainViewHolder>(DiffCallback()) {

    class MainViewHolder(private val binding: FavoriteRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CatModelDomain) {
            binding.apply {
                catImageView.setImageURI(item.url)
            }
        }

        companion object {
            fun from(parent: ViewGroup): MainViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(
                    R.layout.favorite_rv_item,
                    parent, false
                )
                return MainViewHolder(FavoriteRvItemBinding.bind(view))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class DiffCallback : DiffUtil.ItemCallback<CatModelDomain>() {
        override fun areItemsTheSame(
            oldItem: CatModelDomain,
            newItem: CatModelDomain
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: CatModelDomain,
            newItem: CatModelDomain
        ) = (oldItem.id == newItem.id) && (oldItem.url == newItem.url)

    }
}