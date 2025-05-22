package com.example.trabajo12.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo12.R
import com.example.trabajo12.adapters.StockAdapter
import com.example.trabajo12.models.Producto
import com.example.trabajo12.models.ProductosData

class StockTiendaFragment : Fragment() {

    private lateinit var recyclerStock: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_adm_stock_tienda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerStock = view.findViewById(R.id.recyclerViewStock)
        recyclerStock.layoutManager = LinearLayoutManager(requireContext())

        val adapter = StockAdapter(ProductosData.productos)
        recyclerStock.adapter = adapter
    }
}