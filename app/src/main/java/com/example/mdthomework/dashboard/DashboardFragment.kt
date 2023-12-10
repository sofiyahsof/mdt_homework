package com.example.mdthomework.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mdthomework.R
import com.example.mdthomework.databinding.FragmentDashboardBinding
import com.example.mdthomework.network.Transaction

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {
    private lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDashboardBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))
            .get(DashboardViewModel::class.java)


        // Observe balance changes
        viewModel.balance.observe(viewLifecycleOwner) { balanceResponse ->
            // Update UI with balance details
            binding.bankBalance.text = "SGD " + balanceResponse?.balance.toString()
            binding.accountNumber.text = balanceResponse?.accountNumber
            binding.accountName.text = balanceResponse?.accountHolder
        }

        // Observe transaction changes
        viewModel.transactions.observe(viewLifecycleOwner) { transactions: List<Transaction>? ->
            // Update UI with transaction history
            // Use transactions list to populate a RecyclerView or other UI components
        }

        // Fetch initial data
        viewModel.fetchBalance()
        viewModel.fetchTransactions()

        // Set click listener for the "Make Transfer" button
        binding.buttonMakeTransfer.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_transferPageFragment)
        }

        return binding.root
    }
}

