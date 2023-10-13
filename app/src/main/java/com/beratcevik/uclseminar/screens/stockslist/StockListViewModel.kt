package com.beratcevik.uclseminar.screens.stockslist

import com.beratcevik.uclseminar.service.stocks.StocksServiceI

class StockListViewModel(
    private val stockService: StocksServiceI
) {

    fun bind(viewStateHandler: (StockListViewState) -> Unit) {
        stockService.getStocks {
            viewStateHandler.invoke(StockListViewState(rows = it))
        }
    }

    fun uploadAction() {
        stockService.uploadStocks()
    }
}