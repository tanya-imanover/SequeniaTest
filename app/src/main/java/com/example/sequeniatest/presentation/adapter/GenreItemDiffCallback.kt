package com.example.sequeniatest.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.sequeniatest.domain.Genre

class GenreItemDiffCallback: DiffUtil.ItemCallback<Genre>() {
    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem.genre == newItem.genre
    }

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean {
        return oldItem == newItem
    }
}