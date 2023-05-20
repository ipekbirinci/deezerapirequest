package com.example.deezerapirequest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ArtistAdapter(private val artists: List<Artist>) : RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {
    private var onItemClickListener: ArtistAdapter.OnItemClickListener? = null
    fun setOnItemClickListener(listener: ArtistAdapter.OnItemClickListener) {
        onItemClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_cell, parent, false)
        return ArtistViewHolder(view)

    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist = artists[position]
        holder.bind(artist)
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(artist)
        }

    }

    override fun getItemCount(): Int = artists.size

    class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val artistNameTextView: TextView = itemView.findViewById(R.id.categorynametext)
        private val artistImageView: ImageView = itemView.findViewById(R.id.categoryimage)
        fun bind(artist: Artist) {
            if (!artist.picture_medium.isNullOrEmpty() && !artist.name.isNullOrEmpty() && !artist.type.isNullOrEmpty()) {
                artistNameTextView.text = artist.name

                // Load image using a library like Glide or Picasso
                Glide.with(itemView.context)
                    .load(artist.picture_medium)
                    .into(artistImageView)
            }
        }
    }
    interface OnItemClickListener {
        fun onItemClick(artist: Artist)
    }
}
