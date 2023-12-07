package com.example.mdthomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.navigation.Navigation

/**
 * A simple [Fragment] subclass.
 * Use the [TransferPageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TransferPageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_transfer_page, container, false)

        // Find the ImageButton in the layout
        val btnReturnToLogin: ImageButton = view.findViewById(R.id.btnReturnToLogin)

        // Set click listener for the ImageButton
        btnReturnToLogin.setOnClickListener {
            // Use NavController to navigate back to the login page
            Navigation.findNavController(view).navigate(R.id.action_transferPageFragment_to_dashboardFragment)
        }

        return view
    }
}