package com.example.sequeniatest.presentation.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sequeniatest.R

class GenreItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvGenre = itemView.findViewById<TextView>(R.id.tv_genre_item)
}