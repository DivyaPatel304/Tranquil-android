package com.mdev.tranquil

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mdev.tranquil.databinding.ActivityYoutubeListBinding
import okhttp3.*
import java.io.IOException

class YouTubeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityYoutubeListBinding
    private lateinit var adapter: YouTubeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYoutubeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val feeling = intent.getStringExtra("feeling")


        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val apiKey = "AIzaSyBDMYvNORJPdfDJt0u_yu5B8meitJ67gzE" // Replace with your YouTube Data API key
        val searchParameter = feeling + "Meditation"// Replace with your search parameter
        val maxResults = 25

        val url = "https://www.googleapis.com/youtube/v3/search" +
                "?part=snippet&q=$searchParameter&maxResults=$maxResults&key=$apiKey"

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonString = response.body?.string()
                val typeToken = object : TypeToken<YouTubeSearchResponse>() {}.type
                val searchResponse: YouTubeSearchResponse = Gson().fromJson(jsonString, typeToken)

                runOnUiThread {
                    val videoItems = searchResponse.items.take(10) // Take the first 10 items
                    adapter = YouTubeListAdapter(this@YouTubeListActivity, videoItems)
                    binding.recyclerView.adapter = adapter
                }
            }
        })
    }
}
