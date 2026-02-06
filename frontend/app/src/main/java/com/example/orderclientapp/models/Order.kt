package com.example.orderclientapp.models

data class Order(
    val id: Int,
    val items: List<ItemOrder>,
    val driver: Driver,
    val dateCreated: String,
    val establishment: Establishment,
    val status: OrderStatus,
    val comments: String
)