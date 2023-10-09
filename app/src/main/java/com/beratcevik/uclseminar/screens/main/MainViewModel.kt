package com.beratcevik.uclseminar.screens.main

import com.beratcevik.uclseminar.service.stocks.StocksServiceI

class MainViewModel(
    private val stockService: StocksServiceI
) {

    fun bind(viewStateHandler: (MainViewState) -> Unit) {
        stockService.getStocks {
            viewStateHandler.invoke(MainViewState(rows = it))
        }
    }

    fun uploadAction() {
        stockService.uploadStocks()
    }
}
