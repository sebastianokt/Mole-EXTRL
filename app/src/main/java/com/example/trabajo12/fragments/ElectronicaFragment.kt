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
class ElectronicaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_productos, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProductos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val productos = listOf(
            Producto(6, R.drawable.chaqueta, "Chaqueta de cuero", "Diseño moderno, ideal para clima frío.", 599.000, 12, "Chaquetas"),
            Producto(13, R.drawable.chaqueta2, "Chaqueta verde", "Ideal para climas extremadamentes frios.", 350.00, 6, "Chaquetas"),
            Producto(14, R.drawable.chaqueta3, "Chaqueta Azul", "Para climas no tan frios.", 199.900, 8, "Chaquetas"),
            Producto(15, R.drawable.chaqueta4, "Chaqueta roja", "Chaqueta juvenil.", 79.000, 30, "Chaquetas"),
            Producto(16, R.drawable.chaqueta5, "Chaqueta verde lima ", "Chaqueta preciosa verde.", 99.999, 15, "Chaquetas")
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
