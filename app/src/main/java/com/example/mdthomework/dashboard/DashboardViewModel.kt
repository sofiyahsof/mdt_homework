package com.example.mdthomework.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mdthomework.network.ApiService
import com.example.mdthomework.network.BalanceResponse
import com.example.mdthomework.network.Transaction
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DashboardViewModel : ViewModel() {

    private val apiService: ApiService = createApiService()

    private val _balance = MutableLiveData<BalanceResponse>()
    val balance: LiveData<BalanceResponse> get() = _balance

    private val _transactions = MutableLiveData<List<Transaction>>()
    val transactions: LiveData<List<Transaction>> get() = _transactions

    private fun createApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://green-thumb-64168.uc.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiService::class.java)
    }

    fun fetchBalance() {
        apiService.getBalance().enqueue(object : Callback<BalanceResponse> {
            override fun onResponse(call: Call<BalanceResponse>, response: Response<BalanceResponse>) {
                if (response.isSuccessful) {
                    _balance.value = response.body()
                } else {
                    Log.e("API Call", "Failed to fetch balance. Error code: ${response.code()}")
                    // Handle error
                }
            }

            override fun onFailure(call: Call<BalanceResponse>, t: Throwable) {
                Log.e("API Call", "Failed to fetch balance. Error: ${t.message}")
                // Handle failure
            }

        })
    }

    fun fetchTransactions() {
        apiService.getTransactions().enqueue(object : Callback<List<Transaction>> {
            override fun onResponse(call: Call<List<Transaction>>, response: Response<List<Transaction>>) {
                if (response.isSuccessful) {
                    _transactions.value = response.body()
                } else {
                    Log.e("API Call", "Failed to fetch transaction history. Error code: ${response.code()}")
                    // Handle error
                }
            }

            override fun onFailure(call: Call<List<Transaction>>, t: Throwable) {
                Log.e("API Call", "Failed to fetch transaction history. Error: ${t.message}")
                // Handle failure
            }

        })
    }
}

