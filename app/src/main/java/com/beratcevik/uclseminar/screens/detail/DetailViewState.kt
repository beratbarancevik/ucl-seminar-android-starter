package com.beratcevik.uclseminar.screens.detail

data class DetailViewState(
    val title: String,
    val symbol: String,
    val price: String,
    val priceColorId: Int,
    val favoriteButtonTitle: String,
    val imageUrl: String
)