package com.beratcevik.uclseminar.service.stocks

import com.beratcevik.uclseminar.service.stocks.models.Stock

interface StocksServiceI {
    fun getStocks(completionHandler: (List<Stock>) -> Unit)
    fun getStockDetails(stockID: String, completionHandler: (Stock) -> Unit)
    fun updateStockDetail(stockID: String, isFavorite: Boolean)
    fun uploadStocks()
}
