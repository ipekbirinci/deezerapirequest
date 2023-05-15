package com.example.deezerapirequest

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class GenreAdapter(private val genres: List<Genre>) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_cell, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val genre = genres[position]
        holder.bind(genre)
    }

    override fun getItemCount(): Int = genres.size

    class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val genreNameTextView: TextView = itemView.findViewById(R.id.categorynametext)
        private val genreImageView: ImageView = itemView.findViewById(R.id.categoryimage)
        fun bind(genre: Genre) {
            if (!genre.picture_medium.isNullOrEmpty() && !genre.name.isNullOrEmpty() && !genre.type.isNullOrEmpty()) {
                genreNameTextView.text = genre.name

                // Load image using a library like Glide or Picasso
                Glide.with(itemView.context)
                    .load(genre.picture_medium)
                    .into(genreImageView)
            }
        }
    }
}



