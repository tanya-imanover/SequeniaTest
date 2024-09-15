package com.example.sequeniatest.presentation.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sequeniatest.R

class FilmItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val ivFilmPoster = itemView.findViewById<ImageView>(R.id.iv_film_poster)
    val tvFilmName = itemView.findViewById<TextView>(R.id.tv_film_title)
}