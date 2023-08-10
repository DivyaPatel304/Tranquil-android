package com.mdev.tranquil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class Feeling : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feeling)
        val angryButton = findViewById<Button>(R.id.angryButton)

        angryButton.setOnClickListener {
            val message = getString(R.string.angry_message)
            val intent = Intent(this, YouTubeListActivity::class.java)
            intent.putExtra("feeling", "Angry")
            intent.putExtra("image", R.drawable.angry)
            startActivity(intent)
        }

        val frustratedButton = findViewById<Button>(R.id.frustratedButton)

        frustratedButton.setOnClickListener {
            val message = getString(R.string.frustrated_message)
            val intent = Intent(this, YouTubeListActivity::class.java)
            intent.putExtra("feeling", "Frustrated")
            intent.putExtra("image", R.drawable.frustrated)
            startActivity(intent)
        }

        val sadButton = findViewById<Button>(R.id.sad_Button)
        sadButton.setOnClickListener {
            val message = getString(R.string.sad_message)
            val intent = Intent(this, YouTubeListActivity::class.java)
            intent.putExtra("feeling", "Sad")
            intent.putExtra("image", R.drawable.sad)
            startActivity(intent)
        }

        val happyButton = findViewById<Button>(R.id.happyButton)
        happyButton.setOnClickListener {
            val message = getString(R.string.happy_feeling)
            val intent = Intent(this, YouTubeListActivity::class.java)
            intent.putExtra("feeling", "Happy")
            intent.putExtra("image", R.drawable.happy)
            startActivity(intent)
        }

        val stressedButton = findViewById<Button>(R.id.stressedButton)
        stressedButton.setOnClickListener {
            val message = getString(R.string.stressed_feeling)
            val intent = Intent(this, YouTubeListActivity::class.java)
            intent.putExtra("feeling", "Stressed")
            intent.putExtra("image", R.drawable.stressed)
            startActivity(intent)
        }

        val positiveButton = findViewById<Button>(R.id.positiveButton)
        positiveButton.setOnClickListener {
            val message = getString(R.string.positive_feeling)
            val intent = Intent(this, YouTubeListActivity::class.java)
            intent.putExtra("feeling", "Positive")
            intent.putExtra("image", R.drawable.positive)
            startActivity(intent)
        }
    }
}