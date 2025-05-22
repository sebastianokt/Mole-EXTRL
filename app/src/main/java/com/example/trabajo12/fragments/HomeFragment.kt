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
class HomeActivity : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_productos, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProductos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val productos = listOf(
            Producto(17, R.drawable.pantalon2, "Pantalon Cargo ", "Pantalon Cargo cafe.", 899.000, 3, "Pantalones"),
            Producto(8, R.drawable.pantalon, "Jeans Skinny Azul", "Corte ajustado, perfecto para el día a día.", 220.000, 18, "Pantalones"),
            Producto(18, R.drawable.pantalon3, "Pantalon Cargo", "Pantalon cargo gris", 150.009, 10, "Pantalones"),
            Producto(19, R.drawable.pantalon4, "Pantalon gris con manchas", "Pantalon gris con manchas negras.", 590.999, 25, "Pantalones"),
            Producto(20, R.drawable.pantalon5, "Pantalon gris", "Pantalones grises preciosos.", 289.000, 5, "Pantalones")
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