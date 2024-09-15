package com.example.sequeniatest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.sequeniatest.R
import com.example.sequeniatest.domain.Genre

class GenresAdapter : ListAdapter<Genre, GenreItemViewHolder>(GenreItemDiffCallback()) {

    var onShopItemClickListener: ((Genre) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.genge_selected_item
            VIEW_TYPE_DISABLED -> R.layout.genge_item
            else -> throw RuntimeException("Unknown view type $viewType")
        }
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)
        return GenreItemViewHolder(view)
    }


    override fun onBindViewHolder(holder: GenreItemViewHolder, position: Int) {
        val genreItem = getItem(position)
        holder.tvGenre.text = genreItem.genre
        holder.itemView.setOnClickListener {
            onShopItemClickListener?.invoke(genreItem)
        }

    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item.selected) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
    }
}