package com.example.deezerapirequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.launch


class CategoryActivity : AppCompatActivity() {
    private var genreId: Int = 0
    private lateinit var recyclerViewa: RecyclerView
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        recyclerViewa = findViewById(R.id.artistView)
        apiService = ServiceGenerator.buildService(ApiService::class.java)
        genreId = intent.getIntExtra("genreId", 0)
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerViewa.layoutManager = layoutManager

        val adapter = ArtistAdapter(emptyList())
        recyclerViewa.adapter = adapter

        getArtists(genreId)

    }


    private  fun getArtists(genreId:Int) {
        val callback = object : Callback<ArtistResponse> {
            override fun onResponse(call: Call<ArtistResponse>, response: Response<ArtistResponse>) {
                if (response.isSuccessful) {
                    val artistResponse = response.body()
                    if (artistResponse != null) {
                        val artists = artistResponse.data // Artist listesini alın
                        val adapter = ArtistAdapter(artists)
                        recyclerViewa.adapter = adapter
                    } else {
                        // Handle null response
                    }
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<ArtistResponse>, t: Throwable) {
                // Handle failure
            }
        }
        apiService.getArtists(genreId).enqueue(callback)


    }


}