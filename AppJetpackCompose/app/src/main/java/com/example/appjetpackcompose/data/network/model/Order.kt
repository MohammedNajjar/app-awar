package com.example.appjetpackcompose.data.network.model

data class Order(
    val id: String,
    val title: String,
    val body: String,
    val date: String,
    val restaurant: String,
    val customerName: String,
    val status: OrderStatus
)


