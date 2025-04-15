package com.example.trabajo12.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo12.adapters.CarritoAdapter
import com.example.trabajo12.R
import com.example.trabajo12.models.Carrito
import com.example.trabajo12.models.Producto

class CarritoFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var txtTotal: TextView
    private lateinit var txtCantidad: TextView
    private lateinit var adapter: CarritoAdapter
    private var productosCarrito: MutableList<Producto> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_carrito, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewCarrito)
        txtTotal = view.findViewById(R.id.txtTotal)
        txtCantidad = view.findViewById(R.id.txtCantidadProductos)
        val btnPagar = view.findViewById<Button>(R.id.btnPagar)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        cargarCarrito()

        btnPagar.setOnClickListener {
            mostrarConfirmacionPago()
        }

        return view
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun cargarCarrito() {
        productosCarrito = Carrito.obtenerProductos().toMutableList()
        adapter = CarritoAdapter(productosCarrito) { producto ->
            Carrito.eliminarProducto(producto)
            productosCarrito.remove(producto)
            adapter.notifyDataSetChanged()
            actualizarTotales()
        }
        recyclerView.adapter = adapter
        actualizarTotales()
    }

    @SuppressLint("SetTextI18n")
    private fun actualizarTotales() {
        val total = productosCarrito.sumOf { it.precio * it.cantidad }
        val cantidadTotal = productosCarrito.sumOf { it.cantidad }

        txtTotal.text = "Total: $${"%.2f".format(total)}"
        txtCantidad.text = "Productos en el carrito: $cantidadTotal"
    }

    private fun mostrarConfirmacionPago() {
        AlertDialog.Builder(requireContext())
            .setTitle("¿Confirmar pago?")
            .setMessage("¿Estás seguro de que quieres pagar estos productos?")
            .setPositiveButton("Sí") { _, _ ->
                Carrito.limpiarCarrito()
                cargarCarrito()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}
