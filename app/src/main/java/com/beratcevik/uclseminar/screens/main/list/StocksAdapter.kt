package com.beratcevik.uclseminar.screens.main.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beratcevik.uclseminar.R
import com.beratcevik.uclseminar.databinding.StockRowBinding
import com.beratcevik.uclseminar.service.stocks.models.Stock

class StocksAdapter(
    private val context: Context,
    var stocks: List<Stock>,
    private val onRowTap: (Stock) -> Unit
) : RecyclerView.Adapter<StocksAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: StockRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val stock = stocks[position]
                    onRowTap(stock)
                }
            }
        }

        fun bind(book: Stock) {
            binding.textView.text = book.title
            val image = if (book.favorite) {
                context.resources.getDrawable(R.drawable.baseline_star_24)
            } else {
                context.resources.getDrawable(R.drawable.baseline_star_border_24)
            }
            binding.imageView.background = image
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = StockRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(stocks[position])
    }

    override fun getItemCount() = stocks.size
}