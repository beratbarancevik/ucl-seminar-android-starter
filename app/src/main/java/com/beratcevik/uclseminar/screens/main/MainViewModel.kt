package com.beratcevik.uclseminar.screens.main

import com.beratcevik.uclseminar.service.stocks.StocksServiceI

class MainViewModel(
    private val stockService: StocksServiceI
) {

    fun uploadAction() {
        stockService.uploadStocks()
    }
}
