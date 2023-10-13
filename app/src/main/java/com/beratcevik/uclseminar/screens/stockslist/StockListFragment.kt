package com.beratcevik.uclseminar.screens.stockslist

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.beratcevik.uclseminar.starter.databinding.FragmentStockListBinding
import com.beratcevik.uclseminar.screens.main.MainActivity
import com.beratcevik.uclseminar.service.stocks.StockService
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class StockListFragment : Fragment() {

    private var _binding: FragmentStockListBinding? = null
    private val binding get() = _binding!!
    private val viewModel = StockListViewModel(StockService(Firebase.firestore))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStockListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = StockListAdapter(requireContext(), emptyList()) {
            // TODO: navigate to detail
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.bind {
            adapter.stocks = it.rows
            adapter.notifyDataSetChanged()
        }
    }
}