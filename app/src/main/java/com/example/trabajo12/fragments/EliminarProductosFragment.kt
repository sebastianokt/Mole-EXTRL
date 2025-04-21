package com.example.trabajo12.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo12.R
import com.example.trabajo12.adapters.ProductosAdapter
import com.example.trabajo12.models.Producto

class EliminarProductosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productosAdapter: ProductosAdapter
    private lateinit var productos: MutableList<Producto>
    private lateinit var btnEliminarSeleccionados: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_adm_eliminar_productos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewEliminarProductos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        productos = obtenerListaDeProductos()

        productosAdapter = ProductosAdapter(productos)
        recyclerView.adapter = productosAdapter

        btnEliminarSeleccionados = view.findViewById(R.id.btnEliminarSeleccionados)
        btnEliminarSeleccionados.setOnClickListener {
            eliminarProductosSeleccionados()
        }
    }

    private fun eliminarProductosSeleccionados() {
        val productosSeleccionados = productosAdapter.obtenerProductosSeleccionados()

        if (productosSeleccionados.isNotEmpty()) {
            productos.removeAll(productosSeleccionados)
            productosAdapter = ProductosAdapter(productos) // reinicia adaptador para limpiar selección
            recyclerView.adapter = productosAdapter

            Toast.makeText(requireContext(), "Productos eliminados correctamente", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Por favor, selecciona productos para eliminar", Toast.LENGTH_SHORT).show()
        }
    }

    private fun obtenerListaDeProductos(): MutableList<Producto> {
        return mutableListOf(

        )
    }
}
