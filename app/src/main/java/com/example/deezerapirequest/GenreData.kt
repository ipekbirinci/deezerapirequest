package com.example.deezerapirequest

data class GenreResponse(val data: List<Genre>)

data class Genre(
    val id: Int,
    val name: String,
    val picture: String,
    val picture_small: String,
    val picture_medium: String,
    val picture_big: String,
    val picture_xl: String,
    val type: String
)
