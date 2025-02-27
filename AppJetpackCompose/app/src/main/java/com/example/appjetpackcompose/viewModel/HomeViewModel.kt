package com.example.appjetpackcompose.viewModel

import androidx.lifecycle.ViewModel
import com.example.appjetpackcompose.data.network.model.Order
import com.example.appjetpackcompose.data.network.model.OrderStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders.asStateFlow()

    init {
        // Mock data
        _orders.value = listOf(
            Order(
                id = "#1000356",
                title = "",
                body = "",
                date = "August 24, 2022/07:25 pm",
                restaurant = "Kintaki Rest.",
                customerName = "Karlos Karry",
                status = OrderStatus.PENDING
            ),
            Order(
                id = "#1000356",
                title = "",
                body = "",
                date = "August 24, 2022/07:25 pm",
                restaurant = "Kintaki Rest.",
                customerName = "Karlos Karry",
                status = OrderStatus.PREPARE
            ),
            Order(
                id = "#1000356",
                title = "",
                body = "",
                date = "August 24, 2022/07:25 pm",
                restaurant = "Kintaki Rest.",
                customerName = "Karlos Karry",
                status = OrderStatus.PICKUP
            ),
            Order(
                id = "#1000356",
                title = "",
                body = "",
                date = "August 24, 2022/07:25 pm",
                restaurant = "Kintaki Rest.",
                customerName = "Karlos Karry",
                status = OrderStatus.DELIVER
            )
        )
    }

    fun deleteOrder(order: Order) {
        _orders.value = _orders.value.filter { it.id != order.id }
    }
}