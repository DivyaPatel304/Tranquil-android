package com.mdev.tranquil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
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

        // Retrieve the email from the intent extras
        val email = intent.getStringExtra("email")

        // Now you have the email and can use it as needed
        if (email != null) {
            // You can display the email or use it for any other purpose
            // For example, you could set it as a text for a TextView
            val emailTextView = findViewById<TextView>(R.id.editTextTextEmailAddress)
            emailTextView.text = email
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
