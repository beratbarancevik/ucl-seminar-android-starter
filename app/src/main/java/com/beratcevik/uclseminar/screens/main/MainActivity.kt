package com.beratcevik.uclseminar.screens.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.beratcevik.uclseminar.R
import com.beratcevik.uclseminar.databinding.ActivityMainBinding
import com.beratcevik.uclseminar.screens.detail.DetailActivity
import com.beratcevik.uclseminar.screens.main.list.StocksAdapter
import com.beratcevik.uclseminar.service.stocks.StockService
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel(StockService(Firebase.firestore))

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)

        val adapter = StocksAdapter(this, emptyList()) {
            val myIntent = Intent(this, DetailActivity::class.java)
            myIntent.putExtra("stockID", it.id)
            this.startActivity(myIntent)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.bind {
            adapter.stocks = it.rows
            adapter.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_upload_stocks -> {
                viewModel.uploadAction()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}