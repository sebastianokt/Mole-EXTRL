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
import com.example.trabajo12.models.Carrito
import com.example.trabajo12.models.Producto

class ProductosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_productos, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProductos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val productos = listOf(

            Producto(6, R.drawable.chaqueta, "Chaqueta de cuero", "Diseño moderno, ideal para clima frío.", 599.000, 12, "Chaquetas"),
            Producto(7, R.drawable.camiseta , "Camiseta Oversize", "Estilo urbano, 100% algodón.", 120.000, 20, "Camisa"),
            Producto(8, R.drawable.pantalon, "Jeans Skinny Azul", "Corte ajustado, perfecto para el día a día.", 220.000, 18, "Pantalones"),
            Producto(9, R.drawable.zapatos2, "Zapatos negros", "Zapatos preciosos negros.", 180.500, 14, "Zapatos"),
            Producto(10, R.drawable.zapatos, "Zapatos Deportivos ", "Con amortiguación Air para mayor comodidad.", 499.999, 9, "Zapatos"),

            Producto(1, R.drawable.vestidos, "Vestido negro", "Vestido negro manga larga.", 159.999, 10, "Vestidos"),
            Producto(2, R.drawable.vestido2, "Vestido rojo", "Vestido precioso rojo", 199.500, 7, "Vestidos"),
            Producto(3, R.drawable.vestido3, "Vestido de hojas rojas", "Vestido blanco con hojas rojas", 349.999, 15, "Vestidos"),
            Producto(4, R.drawable.camisa2, "Camisa Azul", "Camisa Azul preciosa.", 189.000, 5, "Camisa"),
            Producto(5, R.drawable.camisa3, "Camiseta blanca con puntos", "Camiseta blanca con puntos muy buenas.", 199.999, 8, "Camisa"),

            Producto(11, R.drawable.camisa4, "Camiseta negra", "Iluminación suave con puerto USB.", 89.999, 25, "Camisa"),
            Producto(12, R.drawable.camisa5, "Camisa verde", "100% algodón.", 139.500, 10, "Camisa"),
            Producto(13, R.drawable.chaqueta2, "Chaqueta verde", "Ideal para climas extremadamentes frios.", 350.00, 6, "Chaquetas"),
            Producto(14, R.drawable.chaqueta3, "Chaqueta Azul", "Para climas no tan frios.", 199.900, 8, "Chaquetas"),
            Producto(15, R.drawable.chaqueta4, "Chaqueta roja", "Chaqueta juvenil.", 79.000, 30, "Chaquetas"),
            Producto(16, R.drawable.chaqueta5, "Chaqueta verde lima ", "Chaqueta preciosa verde.", 99.999, 15, "Chaquetas"),
            Producto(17, R.drawable.pantalon2, "Pantalon Cargo ", "Pantalon Cargo cafe.", 899.000, 3, "Pantalones"),

            Producto(18, R.drawable.pantalon3, "Pantalon Cargo", "Pantalon cargo gris", 150.009, 10, "Pantalones"),
            Producto(19, R.drawable.pantalon4, "Pantalon gris con manchas", "Pantalon gris con manchas negras.", 590.999, 25, "Pantalones"),
            Producto(20, R.drawable.pantalon5, "Pantalon gris", "Pantalones grises preciosos.", 289.000, 5, "Pantalones")
        )

        val adapter = ProductosAdapter(productos)
        recyclerView.adapter = adapter

        val btnIrAlCarrito = view.findViewById<FloatingActionButton>(R.id.btnIrCarrito)
        btnIrAlCarrito.setOnClickListener {          findNavController().navigate(R.id.CarritoFragment)
        }

        return view
    }
}
