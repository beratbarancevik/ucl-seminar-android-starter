package com.beratcevik.uclseminar.service.stocks

import android.util.Log
import com.beratcevik.uclseminar.service.stocks.models.Stock
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class StockService(
    private val db: FirebaseFirestore
) : StocksServiceI {

    override fun getStocks(completionHandler: (List<Stock>) -> Unit) {
        db.collection("stocks")
            .orderBy("title")
            .addSnapshotListener { snapshot, error ->
                error?.let {
                    Log.w(
                        this.javaClass.name,
                        "Error getting stocks",
                        it
                    )
                }

                snapshot?.let {
                    completionHandler.invoke(
                        it.documents.mapNotNull { document ->
                            val model = document.toObject(Stock::class.java)
                            model?.let {
                                model.id = document.id
                            }
                            model
                        }
                    )
                }
            }
    }

    override fun getStockDetails(stockID: String, completionHandler: (Stock) -> Unit) {
        db.collection("stocks").document(stockID)
            .addSnapshotListener { snapshot, error ->
                error?.let {
                    Log.w(
                        this.javaClass.name,
                        "Error getting stock",
                        it
                    )
                }

                snapshot?.let {
                    it.toObject(Stock::class.java)?.let { stock ->
                        completionHandler.invoke(stock)
                    }
                }
            }
    }

    override fun updateStockDetail(stockID: String, isFavorite: Boolean) {
        db.collection("stocks").document(stockID).set(
            mapOf("favorite" to isFavorite),
            SetOptions.merge()
        )
    }

    override fun uploadStocks() {
        val stocks = listOf(
            Stock(
                id = "id-1",
                title = "Tesla",
                symbol = "TSLA",
                price = 120.6,
                logoUrl = "https://oceansquare.com/wp-content/uploads/2018/04/tesla-logo-500.jpg",
                favorite = false
            ),
            Stock(
                id = "id-2",
                title = "Apple",
                symbol = "AAPL",
                price = 45.3,
                logoUrl = "https://i.pinimg.com/474x/b0/d2/6e/b0d26e8122dffa8a51081f7f814581d7.jpg",
                favorite = false
            ),
            Stock(
                id = "id-3",
                title = "Meta",
                symbol = "META",
                price = 935.2,
                logoUrl = "https://img.freepik.com/premium-vector/meta-company-logo_265339-667.jpg",
                favorite = false
            )
        )
        stocks.forEach {
            db.collection("stocks").document(it.id).set(it, SetOptions.merge())
        }
    }
}