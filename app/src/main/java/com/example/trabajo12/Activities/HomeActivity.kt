package com.example.trabajo12.Activities

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
            Producto(11, R.drawable.lampara, "Lámpara de Mesa LED", "Iluminación suave con puerto USB.", 89.999, 25, "Hogar"),
            Producto(12, R.drawable.sabanas, "Set de Sábanas Queen", "100% algodón con 4 piezas.", 139.500, 10, "Hogar"),
            Producto(13, R.drawable.silla, "Silla Ergonómica", "Ideal para home office, con soporte lumbar.", 350.00, 6, "Hogar"),
            Producto(14, R.drawable.cafetera, "Cafetera", "Para 12 tazas, con apagado automático.", 199.900, 8, "Hogar"),
            Producto(15, R.drawable.almohada, "Almohada", "Espuma con memoria, ideal para cervicales.", 79.000, 30, "Hogar")
        )

        val adapter = ProductosAdapter(productos)
        recyclerView.adapter = adapter

        val btnIrAlCarrito = view.findViewById<FloatingActionButton>(R.id.btnIrCarrito)
        btnIrAlCarrito?.setOnClickListener {
            findNavController().navigate(R.id.carritoFragment)
        }

        return view
    }
}