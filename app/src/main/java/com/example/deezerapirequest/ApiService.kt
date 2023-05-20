package com.example.deezerapirequest

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/genre")
     fun getGenres(): Call<GenreResponse>

    @GET("/genre/{genre_id}/artists")
    fun getArtists(@Path("genre_id") id: Int): Call<ArtistResponse>

    @GET("/artist/{artist_id}")
    fun getAlbums(@Path("artist_id") id: Int): Call<AlbumsResponse>

}
