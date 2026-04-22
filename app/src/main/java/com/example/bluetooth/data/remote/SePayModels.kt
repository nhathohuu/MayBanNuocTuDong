package com.example.bluetooth.data.remote

import com.google.gson.annotations.SerializedName

data class SePayResponse(
    val transactions: List<Transaction>
)

data class Transaction(
    val id: String,
    @SerializedName("amount_in")
    val amountIn: Double,
    @SerializedName("transaction_content")
    val content: String,
    @SerializedName("transaction_date")
    val date: String
)
