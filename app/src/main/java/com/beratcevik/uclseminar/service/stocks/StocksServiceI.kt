package com.beratcevik.uclseminar.service.stocks

import com.beratcevik.uclseminar.service.stocks.models.Stock

interface StocksServiceI {
    fun getStocks(completionHandler: (List<Stock>) -> Unit)
    fun uploadStocks()
}
