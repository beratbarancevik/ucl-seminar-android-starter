package com.beratcevik.uclseminar.screens.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.beratcevik.uclseminar.starter.R
import com.beratcevik.uclseminar.starter.databinding.ActivityMainBinding
import com.beratcevik.uclseminar.screens.stockslist.StockListFragment
import com.beratcevik.uclseminar.service.stocks.StockService
import com.beratcevik.uclseminar.service.stocks.models.Stock
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel(StockService(Firebase.firestore))
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace(R.id.fragmentContainer, StockListFragment())
            }
        }

        supportFragmentManager.addOnBackStackChangedListener {
            invalidateOptionsMenu()
            if (supportFragmentManager.backStackEntryCount == 0) {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val isHomeScreen = supportFragmentManager.backStackEntryCount == 0
        menu.findItem(R.id.action_upload_stocks)?.isVisible = isHomeScreen
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                supportFragmentManager.popBackStack()
                true
            }

            R.id.action_upload_stocks -> {
                viewModel.uploadAction()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}