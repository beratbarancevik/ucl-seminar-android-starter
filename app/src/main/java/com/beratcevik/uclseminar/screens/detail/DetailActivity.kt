package com.beratcevik.uclseminar.screens.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.beratcevik.uclseminar.databinding.ActivityDetailBinding
import com.beratcevik.uclseminar.service.stocks.StockService
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val stockID = intent.getStringExtra("stockID") ?: ""
        viewModel = DetailViewModel(stockID, StockService(Firebase.firestore))

        viewModel.bind {
            Glide.with(this)
                .load(it.imageUrl)
                .into(binding.imageView)
            binding.titleTextView.text = it.title
            binding.symbolTextView.text = it.symbol
            binding.priceTextView.text = it.price
            binding.priceTextView.setTextColor(resources.getColor(it.priceColorId))
            binding.favoriteButton.text = it.favoriteButtonTitle
        }

        binding.favoriteButton.setOnClickListener {
            viewModel.favoriteAction()
        }
    }
}