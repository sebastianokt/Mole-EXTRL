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

class AccesoriosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_productos, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProductos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        val productos: List<Producto> = listOf(
            Producto(18, R.drawable.mochila, "Mochila Antirrobo", "Compartimento para laptop, resistente al agua.", 150.000, 10, "Accesorios"),
            Producto(19, R.drawable.gorra, "Gorra Negra", "Ajustable, con diseño bordado.", 59.999, 25, "Accesorios"),
            Producto(20, R.drawable.reloj, "Reloj Inteligente", "Monitor de ritmo cardíaco y pasos.", 289.000, 5, "Accesorios")
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
