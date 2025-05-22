package com.example.trabajo12.fragments

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo12.R
import com.example.trabajo12.adapters.ProductosAdapter
import com.example.trabajo12.models.Carrito
import com.example.trabajo12.models.Producto

class EliminarProductosFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnEliminar: Button
    private lateinit var adapter: ProductosAdapter
    private var productosCarrito: MutableList<Producto> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_adm_eliminar_productos, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewEliminarProductos)
        btnEliminar = view.findViewById(R.id.btnEliminarSeleccionados)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        cargarProductos()

        btnEliminar.setOnClickListener {
            eliminarProductosSeleccionados()
        }

        return view
    }

    private fun cargarProductos() {
        productosCarrito = Carrito.obtenerProductos().toMutableList()
        adapter = ProductosAdapter(productosCarrito)
        recyclerView.adapter = adapter
    }

    private fun eliminarProductosSeleccionados() {
        val seleccionados = adapter.obtenerProductosSeleccionados()

        if (seleccionados.isEmpty()) {
            Toast.makeText(requireContext(), "No has seleccionado productos", Toast.LENGTH_SHORT).show()
            return
        }

        for (producto in seleccionados) {
            Carrito.eliminarProducto(producto)
            productosCarrito.remove(producto)
        }

        adapter.eliminarProductosSeleccionados()
        Toast.makeText(requireContext(), "Productos eliminados", Toast.LENGTH_SHORT).show()
    }
}
