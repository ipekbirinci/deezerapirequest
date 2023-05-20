package com.example.deezerapirequest

data class ArtistResponse(val data: List<Artist>?)

data class Artist(
    val id: Int?=null,
    val name: String?=null,
    val picture_small: String?=null,
    val picture_medium: String?=null,
    val picture_big: String?=null,
    val picture_xl: String?=null,
    val radio: Boolean?=null,
    val tracklist: String?=null,
    val type: String?=null
)