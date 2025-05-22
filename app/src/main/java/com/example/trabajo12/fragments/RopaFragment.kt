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
            Producto(7, R.drawable.camiseta , "Camiseta Oversize", "Estilo urbano, 100% algodón.", 120.000, 20, "Camisa"),
            Producto(4, R.drawable.camisa2, "Camisa Azul", "Camisa Azul preciosa.", 189.000, 5, "Camisa"),
            Producto(5, R.drawable.camisa3, "Camiseta blanca con puntos", "Camiseta blanca con puntos muy buenas.", 199.999, 8, "Camisa"),
            Producto(11, R.drawable.camisa4, "Camiseta negra", "Iluminación suave con puerto USB.", 89.999, 25, "Camisa"),
            Producto(12, R.drawable.camisa5, "Camisa verde", "100% algodón.", 139.500, 10, "Camisa")
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