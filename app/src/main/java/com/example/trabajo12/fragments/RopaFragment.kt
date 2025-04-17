package com.example.trabajo12.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo12.adapters.ProductosAdapter
import com.example.trabajo12.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.trabajo12.models.Producto
class RopaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_productos, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProductos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val productos = listOf(
            Producto(6, R.drawable.chaqueta, "Chaqueta de cuero", "Diseño moderno, ideal para clima frío.", 599.000, 12, "Ropa"),
            Producto(7, R.drawable.camiseta, "Camiseta Oversize", "Estilo urbano, 100% algodón.", 120.000, 20, "Ropa"),
            Producto(8, R.drawable.pantalon, "Jeans Skinny Azul", "Corte ajustado, perfecto para el día a día.", 220.000, 18, "Ropa"),
            Producto(9, R.drawable.sudadera, "Sudadera con Capucha", "Con interior afelpado, ideal para invierno.", 180.500, 14, "Ropa"),
            Producto(10, R.drawable.zapatos, "Zapatos Deportivos Nike", "Con amortiguación Air para mayor comodidad.", 499.999, 9, "Ropa")
        )

        val adapter = ProductosAdapter(productos)
        recyclerView.adapter = adapter

        val btnIrAlCarrito = view.findViewById<FloatingActionButton>(R.id.btnIrCarrito)
        btnIrAlCarrito?.setOnClickListener {
            findNavController().navigate(R.id.CarritoFragment)
        }

        return view
    }
}