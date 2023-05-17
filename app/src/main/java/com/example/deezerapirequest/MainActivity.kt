package com.example.deezerapirequest

import android.content.Intent
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
import kotlin.math.log
class MainActivity : AppCompatActivity(), GenreAdapter.OnItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.categoryView)
        apiService = ServiceGenerator.buildService(ApiService::class.java)

        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager

        val adapter = GenreAdapter(emptyList())
        adapter.setOnItemClickListener(this)
        recyclerView.adapter = adapter

        getGenres()
    }

    override fun onItemClick(genre: Genre) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra("genreId", genre.id)
        intent.putExtra("categoryName", genre.name)
        startActivity(intent)
    }

    private fun getGenres() {
        val callback = object : Callback<GenreResponse> {
            override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                if (response.isSuccessful) {
                    val genreResponse = response.body()
                    if (genreResponse != null) {
                        val genres = genreResponse.data
                        val adapter = GenreAdapter(genres)
                        adapter.setOnItemClickListener(this@MainActivity)
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

        apiService.getGenres().enqueue(callback)
    }
}
