package com.example.trabajo12.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo12.adapters.ProductosAdapter
import com.example.trabajo12.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.trabajo12.models.Producto
import com.example.trabajo12.models.ProductosData

class ProductosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_productos, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProductos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        val adapter = ProductosAdapter(ProductosData.productos)  // ← Uso del singleton
        recyclerView.adapter = adapter

        val btnIrAlCarrito = view.findViewById<FloatingActionButton>(R.id.btnIrCarrito)
        btnIrAlCarrito.setOnClickListener {          findNavController().navigate(R.id.CarritoFragment)
        }

        return view
    }
}
