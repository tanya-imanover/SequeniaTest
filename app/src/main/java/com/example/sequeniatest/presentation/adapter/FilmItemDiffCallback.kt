package com.example.sequeniatest.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.sequeniatest.domain.Film

class FilmItemDiffCallback: DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}