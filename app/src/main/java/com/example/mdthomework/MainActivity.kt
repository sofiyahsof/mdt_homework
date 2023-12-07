package com.example.mdthomework

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getPreferences(Context.MODE_PRIVATE)
    }

    // Function to save the authentication token
    fun saveToken(token: String?) {
        sharedPreferences.edit().putString("auth_token", token).apply()
    }

    // Function to retrieve the authentication token
    fun getToken(): String? {
        return sharedPreferences.getString("auth_token", null)
    }
}
