package com.mdev.tranquil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class Result : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val feeling = intent.getStringExtra("feeling")

        val resultImageView = findViewById<ImageView>(R.id.resultImageView)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        when (feeling) {
            "Angry" -> {
                resultImageView.setImageResource(R.drawable.angry)
                resultTextView.text = getString(R.string.angry_message)
            }
            "Frustrated" -> {
                resultImageView.setImageResource(R.drawable.frustrated)
                resultTextView.text = getString(R.string.frustrated_message)
            }
            "Sad" -> {
                resultImageView.setImageResource(R.drawable.sad)
                resultTextView.text = getString(R.string.sad_message)
            }
            "Happy" -> {
                resultImageView.setImageResource(R.drawable.happy)
                resultTextView.text = getString(R.string.happy_feeling)
            }
            "Stressed" -> {
                resultImageView.setImageResource(R.drawable.stressed)
                resultTextView.text = getString(R.string.stressed_feeling)
            }
            "Positive" -> {
                resultImageView.setImageResource(R.drawable.positive)
                resultTextView.text = getString(R.string.positive_feeling)
            }
        }
    }
}
