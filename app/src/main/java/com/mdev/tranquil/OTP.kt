package com.mdev.tranquil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog


class OTP : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        val verifyButton = findViewById<Button>(R.id.verifyButton)
        verifyButton.setOnClickListener {
            val intent = Intent(this, Feeling::class.java)
            startActivity(intent)
        }
    }

    fun showCodeSentAlert(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Code Sent")
        builder.setMessage("Code has been sent successfully to your mobile number.")
        builder.setPositiveButton("OK", null)
        builder.show()
    }
}
