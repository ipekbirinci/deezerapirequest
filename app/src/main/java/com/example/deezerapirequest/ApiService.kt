package com.example.deezerapirequest

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/genre")
     fun getGenres(): Call<GenreResponse>
}
