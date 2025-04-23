package com.example.trabajo12.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo12.R
import com.example.trabajo12.adapters.StockAdapter
import com.example.trabajo12.models.Producto

class StockTiendaFragment : Fragment() {

    private lateinit var recyclerStock: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_adm_stock_tienda, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerStock = view.findViewById(R.id.recyclerViewStock)
        recyclerStock.layoutManager = LinearLayoutManager(requireContext())

        val productos = obtenerListaDeProductos(requireContext())
        recyclerStock.adapter = StockAdapter(productos)
    }

    private fun obtenerListaDeProductos(context: Context): MutableList<Producto> {
        val prefs = context.getSharedPreferences("productos", Context.MODE_PRIVATE)
        val listaString = prefs.getStringSet("productos_lista", emptySet()) ?: emptySet()
        val listaProductos = mutableListOf<Producto>()

        for (linea in listaString) {
            val partes = linea.split("|")
            if (partes.size >= 7) {
                try {
                    val producto = Producto(
                        id = partes[0].toInt(),
                        imagen = partes[1].toInt(),
                        nombre = partes[2],
                        descripcion = partes[3],
                        precio = partes[4].toDouble(),
                        cantidad = partes[5].toInt(),
                        categoria = partes[6]
                    )
                    listaProductos.add(producto)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        return listaProductos
    }
}
