package com.mdev.tranquil

import DatabaseHelper
import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Signup : AppCompatActivity() {

    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val dbHelper = DatabaseHelper(this)
        db = dbHelper.writableDatabase
    }

    fun goToLogin(view: View) {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    fun startOTPActivity(view: View?) {
        val emailEditText = findViewById<EditText>(R.id.email_edittext)
        val passwordEditText = findViewById<EditText>(R.id.password_edittext)

        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        val values = ContentValues().apply {

            put(DatabaseHelper.KEY_EMAIL, email)
            put(DatabaseHelper.KEY_PASSWORD, password)
        }

        db.insert(DatabaseHelper.TABLE_USER, null, values)

        val intent = Intent(this, OTP::class.java)
        startActivity(intent)
    }

}

