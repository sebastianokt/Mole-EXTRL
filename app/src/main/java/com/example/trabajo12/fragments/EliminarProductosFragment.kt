package com.example.trabajo12.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo12.R
import com.example.trabajo12.adapters.EliminarProductoAdapter
import com.example.trabajo12.models.Producto
import com.example.trabajo12.models.ProductosData

class EliminarProductosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EliminarProductoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_adm_eliminar_productos, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewEliminarProductos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = EliminarProductoAdapter(ProductosData.productos) { producto ->
            eliminarProducto(producto)
        }

        recyclerView.adapter = adapter

        return view
    }

    private fun eliminarProducto(producto: Producto) {
        val position = ProductosData.productos.indexOfFirst { it.id == producto.id }
        if (position != -1) {
            ProductosData.productos.removeAt(position)
            adapter.notifyItemRemoved(position)
            Toast.makeText(
                requireContext(),
                "Producto eliminado: ${producto.nombre}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}