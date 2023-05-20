package com.example.deezerapirequest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AlbumAdapter(private val albums: List<AlbumsData>) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.albums_cell, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albums[position]
        holder.bind(album)
    }

    override fun getItemCount(): Int = albums.size

    class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val albumNameTextView: TextView = itemView.findViewById(R.id.albumismi)
        private val albumImageView: ImageView = itemView.findViewById(R.id.albumpicture)
        private val albumReliaseDateView: TextView = itemView.findViewById(R.id.cikistarihi)
        private val sanatciImageView: ImageView = itemView.findViewById(R.id.sanatciimage)

        fun bind(album: AlbumsData) {
            albumNameTextView.text = album.name
            albumReliaseDateView.text=album.link

            // Load image using a library like Glide or Picasso
            Glide.with(itemView.context)
                .load(album.picture_small)
                .into(albumImageView)

        }
        fun bind(artist: Artist) {
            // Load image using a library like Glide or Picasso
            Glide.with(itemView.context)
                .load(artist.picture_small)
                .into(sanatciImageView)

        }
    }
}
