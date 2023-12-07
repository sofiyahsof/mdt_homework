package com.example.mdthomework.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @GET("/balance")
    fun getBalance(): Call<BalanceResponse>

    @GET("/transactions")
    fun getTransactions(): Call<List<Transaction>>
}

data class LoginRequest(val username: String, val password: String)
data class LoginResponse(val token: String)
data class BalanceResponse(val balance: Double, val accountNumber: String, val accountHolder: String)
data class Transaction(val id: String, val amount: Double, val description: String, val date: String)

