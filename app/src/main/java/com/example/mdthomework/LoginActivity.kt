package com.example.mdthomework

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mdthomework.network.ApiService
import com.example.mdthomework.network.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private val baseUrl = "https://green-thumb-64168.uc.r.appspot.com"
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login_page) //need to check if layout is pointing to correct reference

        // Initialize Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create an instance of ApiService
        apiService = retrofit.create(ApiService::class.java)

        // Perform login
        performLogin("test", "asdasd")
    }

    private fun performLogin(username: String, password: String) {
        GlobalScope.launch(Dispatchers.Main) {
            try {
                // Make the login request
                val response = apiService.login(LoginRequest(username, password)).execute()

                if (response.isSuccessful) {
                    // Extract and store the token
                    val token = response.body()?.token
                    Log.d("LoginActivity", "JWT Token: $token")

                } else {
                    Log.e("LoginActivity", "Login failed. Response: ${response.errorBody()}")
                }
            } catch (e: Exception) {
                Log.e("LoginActivity", "Error during login", e)
            }
        }
    }
}
