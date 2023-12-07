package com.example.mdthomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.mdthomework.databinding.FragmentLoginPageBinding


/**
 * A simple [Fragment] subclass.
 * Use the [LoginPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginPageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLoginPageBinding.inflate(inflater, container, false)

        /* Navigates to the dashboard when login button is clicked */
        binding.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginPageFragment_to_dashboardFragment)
        }

        /* Navigates to the Register page when register button is clicked */
        binding.buttonRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginPageFragment_to_registerPageFragment)
        }

        return binding.root
    }
}
