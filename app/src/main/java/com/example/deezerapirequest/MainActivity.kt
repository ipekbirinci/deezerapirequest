package com.example.deezerapirequest

import android.graphics.drawable.ClipDrawable.HORIZONTAL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.categoryView)
        apiService = ServiceGenerator.buildService(ApiService::class.java)

        // Set layout manager
        val layoutManager = StaggeredGridLayoutManager(2, HORIZONTAL)
        recyclerView.layoutManager = layoutManager
val linear=findViewById<LinearLayout>(R.id.linearLayout)
        // Create an empty adapter initially
        val adapter = GenreAdapter(emptyList())
        recyclerView.adapter = adapter

        // Make the API call
        getGenres()
    }

    private fun getGenres() {
        val callback = object : Callback<GenreResponse> {
            override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                if (response.isSuccessful) {
                    val genreResponse = response.body()
                    if (genreResponse != null) {
                        val genres = genreResponse.data
                        val adapter = GenreAdapter(genres)
                        recyclerView.adapter = adapter


                    } else {
                        // Handle null response
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                // Handle failure
            }
        }

        // Make the API call using enqueue
        apiService.getGenres().enqueue(callback)
    }
}
