package com.example.mdthomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.mdthomework.databinding.FragmentLoginPageBinding
import com.example.mdthomework.databinding.FragmentRegisterPageBinding
import com.example.mdthomework.network.ApiService
import com.example.mdthomework.network.RegisterRequest
import com.example.mdthomework.network.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterPageFragment : Fragment() {

    private lateinit var binding: FragmentRegisterPageBinding
    private lateinit var apiService: ApiService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using view binding
        binding = FragmentRegisterPageBinding.inflate(inflater, container, false)
        val view = binding.root

        // Find the ImageButton in the layout
        val btnReturnToLogin: ImageButton = view.findViewById(R.id.btnReturnToLogin)

        // Set click listener for the ImageButton
        btnReturnToLogin.setOnClickListener {
            // Use NavController to navigate back to the login page
            Navigation.findNavController(view).navigate(R.id.action_registerPageFragment_to_loginPageFragment)
        }

        // Create a Retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://green-thumb-64168.uc.r.appspot.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Create an instance of the ApiService interface
        apiService = retrofit.create(ApiService::class.java)

        // Find the registration button and set a click listener
        val btnRegister: Button = binding.buttonRegister
        btnRegister.setOnClickListener {
            // Call the registration function only if passwords match
            if (passwordsMatch()) {
                registerUser()
            } else {
                // Handle when passwords don't match
                val errorMessage = "Passwords do not match."

                // Show the error message at the bottom of the box
                binding.textErrorMessage.text = errorMessage
                binding.textErrorMessage.visibility = View.VISIBLE
            }
        }

        return view
    }
    // Checks if the input for the passwords are the same
    private fun passwordsMatch(): Boolean {
        val passwordEditText: EditText? = view?.findViewById(R.id.editTextPassword)
        val confirmPasswordEditText: EditText? = view?.findViewById(R.id.editTextConfirmPassword)

        val password = passwordEditText?.text.toString()
        val confirmPassword = confirmPasswordEditText?.text.toString()

        return password == confirmPassword
    }


    private fun registerUser() {
        val username = binding.editTextUsername.text.toString()
        val password = binding.editTextPassword.text.toString()
        val confirmPassword = binding.editTextConfirmPassword.text.toString()

        // Create a RegisterRequest object
        val registerRequest = RegisterRequest(username, password, confirmPassword)

        // Call the registration API
        val call = apiService.register(registerRequest)
        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful) {
                    // Registration successful, handle the response as needed
                    val message = response.body()?.message ?: "Registration successful"
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                } else {
                    // Registration failed, handle the error
                    Toast.makeText(requireContext(), "Registration failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                // Handle network errors or other failures
                Toast.makeText(requireContext(), "Please try again.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
