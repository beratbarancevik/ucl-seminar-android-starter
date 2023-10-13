package com.beratcevik.uclseminar.screens.stockslist

import com.beratcevik.uclseminar.service.stocks.models.Stock

data class StockListViewState(
    val rows: List<Stock>
)