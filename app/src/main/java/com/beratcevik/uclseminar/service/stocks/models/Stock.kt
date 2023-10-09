package com.beratcevik.uclseminar.service.stocks.models

data class Stock(
    var id: String = "",
    val title: String = "",
    val symbol: String = "",
    val price: Double = 0.0,
    val logoUrl: String = "",
    val favorite: Boolean = false
)