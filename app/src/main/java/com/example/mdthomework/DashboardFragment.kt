package com.example.mdthomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mdthomework.databinding.FragmentDashboardBinding

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDashboardBinding.inflate(inflater, container, false)

        /* Navigates to the transfer page when make transfer button is clicked */
        binding.buttonMakeTransfer.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_transferPageFragment)
        }

        return binding.root
    }
}