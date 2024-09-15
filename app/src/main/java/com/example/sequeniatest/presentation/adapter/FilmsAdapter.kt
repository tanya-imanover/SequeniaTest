package com.example.sequeniatest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.sequeniatest.R
import com.example.sequeniatest.domain.Film

class FilmsAdapter : ListAdapter<Film, FilmItemViewHolder>(FilmItemDiffCallback()) {

    var onFilmItemClickListener: ((Film) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmItemViewHolder {
        val layout = R.layout.film_item

        val view = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)

        return FilmItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmItemViewHolder, position: Int) {
        val filmItem = getItem(position)
        Glide.with(holder.itemView)
            .load(filmItem.imageUrl)
            .placeholder(R.drawable.placeholder)
            .centerCrop()
            .into(holder.ivFilmPoster)


        holder.tvFilmName.text = filmItem.localizedName
        holder.itemView.setOnClickListener {
            onFilmItemClickListener?.invoke(filmItem)
        }

    }

}