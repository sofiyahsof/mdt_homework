package com.example.mdthomework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mdthomework.databinding.FragmentLoginPageBinding
import com.example.mdthomework.network.ApiService
import com.example.mdthomework.network.LoginRequest
import com.example.mdthomework.network.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginPageFragment : Fragment() {

    private lateinit var binding: FragmentLoginPageBinding
    private lateinit var apiService: ApiService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginPageBinding.inflate(inflater, container, false)

        // Create a Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://green-thumb-64168.uc.r.appspot.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create an instance of the ApiService interface
        apiService = retrofit.create(ApiService::class.java)

        /* Navigates to the dashboard when login button is clicked */
        binding.buttonLogin.setOnClickListener {
            performLogin()
        }

        /* Navigates to the Register page when register button is clicked */
        binding.buttonRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginPageFragment_to_registerPageFragment)
        }

        return binding.root
    }

    private fun performLogin() {
        val username = binding.editTextUsername.text.toString()
        val password = binding.editTextPassword.text.toString()

        // Create a LoginRequest object
        val loginRequest = LoginRequest(username, password)

        // Call the login API using Retrofit
        val call = apiService.login(loginRequest)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    // Successfully logged in
                    val token = response.body()?.token

                    // Save the token for subsequent requests
                    (activity as? MainActivity)?.saveToken(token)

                    // Navigate to the dashboard
                    findNavController().navigate(R.id.action_loginPageFragment_to_dashboardFragment)
                } else {
                    // Handle unsuccessful response
                    val errorMessage = "Incorrect username or password."

                    // Show the error message at the bottom of the box
                    binding.textErrorMessage.text = errorMessage
                    binding.textErrorMessage.visibility = View.VISIBLE
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Handle network failure
                Toast.makeText(
                    requireContext(),
                    "Please try again.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}

