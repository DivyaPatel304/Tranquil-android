package com.mdev.tranquil

import DatabaseHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog


class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun goToSignUp(view: View) {
        val intent = Intent(this, Signup::class.java)
        startActivity(intent)
    }

    fun startOTPActivity(view: View?) {

        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword)

        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        // check user's credentials dynamically
        val dbHandler = DatabaseHelper(this)
        val user = dbHandler.getUser(email)

        if (user != null && user.password == password) {
            // credentials are correct, start OTP activity
            val intent = Intent(this, OTP::class.java)
            startActivity(intent)
        } else {
            // credentials are incorrect, show an alert dialog
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Incorrect Credentials")
            builder.setMessage("The email and password combination entered is incorrect.")
            builder.setPositiveButton("OK", null)
            val dialog = builder.create()
            dialog.show()
        }
    }
}
