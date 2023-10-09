package com.beratcevik.uclseminar.screens.main

import com.beratcevik.uclseminar.service.stocks.models.Stock

data class MainViewState(
    val rows: List<Stock>
)