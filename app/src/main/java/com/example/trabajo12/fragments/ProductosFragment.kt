package com.example.trabajo12.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo12.R
import com.example.trabajo12.adapters.ProductosAdapter
import com.example.trabajo12.models.Producto
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_productos, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProductos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val productos = listOf(
            Producto(6, R.drawable.chaqueta, "Chaqueta de cuero", "Diseño moderno, ideal para clima frío.", 599000.0, 12, "Chaquetas"),
            Producto(7, R.drawable.camiseta, "Camiseta Oversize", "Estilo urbano, 100% algodón.", 120000.0, 20, "Camisa"),
            Producto(8, R.drawable.pantalon, "Jeans Skinny Azul", "Corte ajustado, perfecto para el día a día.", 220000.0, 18, "Pantalones"),
            Producto(9, R.drawable.zapatos2, "Zapatos negros", "Zapatos preciosos negros.", 180500.0, 14, "Zapatos"),
            Producto(10, R.drawable.zapatos, "Zapatos Deportivos ", "Con amortiguación Air para mayor comodidad.", 499999.0, 9, "Zapatos"),
            Producto(1, R.drawable.vestidos, "Vestido negro", "Vestido negro manga larga.", 159999.0, 10, "Vestidos"),
            Producto(2, R.drawable.vestido2, "Vestido rojo", "Vestido precioso rojo", 199500.0, 7, "Vestidos"),
            Producto(3, R.drawable.vestido3, "Vestido de hojas rojas", "Vestido blanco con hojas rojas", 349999.0, 15, "Vestidos"),
            Producto(4, R.drawable.camisa2, "Camisa Azul", "Camisa Azul preciosa.", 189000.0, 5, "Camisa"),
            Producto(5, R.drawable.camisa3, "Camiseta blanca con puntos", "Camiseta blanca con puntos muy buenas.", 199999.0, 8, "Camisa"),
            Producto(11, R.drawable.camisa4, "Camiseta negra", "Iluminación suave con puerto USB.", 89999.0, 25, "Camisa"),
            Producto(12, R.drawable.camisa5, "Camisa verde", "100% algodón.", 139500.0, 10, "Camisa"),
            Producto(13, R.drawable.chaqueta2, "Chaqueta verde", "Ideal para climas extremadamentes frios.", 350000.0, 6, "Chaquetas"),
            Producto(14, R.drawable.chaqueta3, "Chaqueta Azul", "Para climas no tan frios.", 199900.0, 8, "Chaquetas"),
            Producto(15, R.drawable.chaqueta4, "Chaqueta roja", "Chaqueta juvenil.", 79000.0, 30, "Chaquetas"),
            Producto(16, R.drawable.chaqueta5, "Chaqueta verde lima ", "Chaqueta preciosa verde.", 99999.0, 15, "Chaquetas"),
            Producto(17, R.drawable.pantalon2, "Pantalon Cargo ", "Pantalon Cargo cafe.", 899000.0, 3, "Pantalones"),
            Producto(18, R.drawable.pantalon3, "Pantalon Cargo", "Pantalon cargo gris", 150009.0, 10, "Pantalones"),
            Producto(19, R.drawable.pantalon4, "Pantalon gris con manchas", "Pantalon gris con manchas negras.", 590999.0, 25, "Pantalones"),
            Producto(20, R.drawable.pantalon5, "Pantalon gris", "Pantalones grises preciosos.", 289000.0, 5, "Pantalones")
        )

        // Guardar en SharedPreferences solo si no están guardados
        val prefs = requireContext().getSharedPreferences("ProductosPrefs", Context.MODE_PRIVATE)
        if (!prefs.contains("productos")) {
            val editor = prefs.edit()
            val productosSet = productos.map {
                "${it.id}|${it.imagen}|${it.nombre}|${it.descripcion}|${it.precio}|${it.cantidad}|${it.categoria}"
            }.toSet()
            editor.putStringSet("productos", productosSet)
            editor.apply()
        }

        val adapter = ProductosAdapter(productos)
        recyclerView.adapter = adapter

        val btnIrAlCarrito = view.findViewById<FloatingActionButton>(R.id.btnIrCarrito)
        btnIrAlCarrito.setOnClickListener {
            findNavController().navigate(R.id.CarritoFragment)
        }

        return view
    }
}
