package com.example.bluetooth.data.remote

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SePayApi {
    @GET("transactions/list")
    suspend fun getTransactions(
        @Header("Authorization") token: String,
        @Query("account_number") accountNumber: String,
        @Query("limit") limit: Int = 1
    ): SePayResponse
}
