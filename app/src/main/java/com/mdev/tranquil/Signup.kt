package com.mdev.tranquil

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class Signup : AppCompatActivity() {

    private val client = OkHttpClient()
    private val BASE_URL = "http://10.0.2.2:3000" // Replace with your server's IP address

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        emailEditText = findViewById(R.id.email_edittext)
        passwordEditText = findViewById(R.id.password_edittext)
    }

    fun goToLogin(view: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    fun startOTPActivity(view: View) {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        val jsonObject = JSONObject()
        try {
            jsonObject.put("username", email)
            jsonObject.put("password", password)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val JSON = "application/json; charset=utf-8".toMediaType()
        val body = RequestBody.create(JSON, jsonObject.toString())

        val request = Request.Builder()
            .url("$BASE_URL/register")
            .post(body)
            .build()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    // Registration successful, start OTP activity
                    val intent = Intent(this@Signup, OTP::class.java)
                    startActivity(intent)
                } else {
                    // Registration failed, handle error
                    runOnUiThread {
                        AlertDialog.Builder(this@Signup)
                            .setTitle("Registration Error")
                            .setMessage("Failed to register user. Please check your input or try again later.")
                            .setPositiveButton("OK", null)
                            .create()
                            .show()
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                // Handle error, e.g., display an error dialog
                runOnUiThread {
                    AlertDialog.Builder(this@Signup)
                        .setTitle("Error")
                        .setMessage("Failed to connect to the server. Please try again later.")
                        .setPositiveButton("OK", null)
                        .create()
                        .show()
                }
            }
        }
    }

    // Extension function for creating MediaType
    private fun String.toMediaType(): MediaType {
        return this.toMediaTypeOrNull()!!
    }
}
