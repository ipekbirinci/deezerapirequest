package com.example.deezerapirequest

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/genre")
     fun getGenres(): Call<GenreResponse>

    @GET("/{id}/artist")
    fun getArtists(@Path("id") id: Int): Call<ArtistResponse>

}
