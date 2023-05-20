package com.example.deezerapirequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Albums : AppCompatActivity() {
    private var artistId: Int = 0
    private lateinit var artistPicture: String
    private lateinit var recyclerView: RecyclerView
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_albums)
        recyclerView = findViewById(R.id.albumsview)
        apiService = ServiceGenerator.buildService(ApiService::class.java)
        artistId = intent.getIntExtra("artistId", 0)
        artistPicture= intent.getStringExtra("artistPicture").toString()
        val layoutManager= LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val adapter = ArtistAdapter(emptyList())
        recyclerView.adapter = adapter

        getAlbums(artistId)
    }
    private  fun getAlbums(artistId:Int) {
        val callback = object : Callback<AlbumsResponse> {
            override fun onResponse(call: Call<AlbumsResponse>, response: Response<AlbumsResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Log.d("Service","bodyfull")
                        it.data?.let {
                            Log.d("Service","datafull")
                            val adapter = AlbumAdapter(it)
                            recyclerView.adapter = adapter

                        }?: kotlin.run {
                            Log.d("Service","data empty")
                        }
                    }?: kotlin.run {
                        Log.d("Service","body empty")
                    }
                    val artistResponse = response.body()
                    /*if (artistResponse != null) {
                        val artists = artistResponse.data // Artist listesini al
                        val adapter = ArtistAdapter(artists)
                        recyclerViewa.adapter = adapter
                    } else {
                        // Handle null response
                    }*/
                } else {
                    // Handle unsuccessful response
                }
            }

            override fun onFailure(call: Call<AlbumsResponse>, t: Throwable) {
                // Handle failure
            }
        }
        apiService.getAlbums(artistId).enqueue(callback)


    }


}