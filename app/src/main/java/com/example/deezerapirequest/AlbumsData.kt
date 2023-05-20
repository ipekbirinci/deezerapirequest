package com.example.deezerapirequest

data class AlbumsResponse(val data: List<AlbumsData>?)
data class AlbumsData(
    val id: Int,
    val name: String,
    val link: String,
    val share: String,
    val picture: String,
    val picture_small: String,
    val picture_medium: String,
    val picture_big: String,
    val picture_xl: String,
    val nb_album: Int,
    val nb_fan: Int,
    val radio: Boolean,
    val tracklist: String,
    val type: String
)
