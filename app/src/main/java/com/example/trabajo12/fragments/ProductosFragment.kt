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
            Producto(1, R.drawable.celular, "Smartphone Galaxy A34", "Pantalla AMOLED, cámara triple y batería de larga duración.", 999.999, 10, "Electrónica"),
            Producto(2, R.drawable.lenovo, "Laptop Lenovo i5", "Laptop de alto rendimiento con 16GB RAM y SSD 512GB.", 2199.500, 7, "Electrónica"),
            Producto(3, R.drawable.jbg, "Auriculares Inalámbricos JBL", "Calidad de sonido premium con cancelación de ruido.", 349.999, 15, "Electrónica"),
            Producto(4, R.drawable.tv, "Smart TV 50\"", "Televisor inteligente 4K con Android TV integrado.", 1890.000, 5, "Electrónica"),
            Producto(5, R.drawable.gpro, "Cámara Deportiva GoPro", "Resistente al agua y con grabación en 4K.", 1299.999, 8, "Electrónica"),

            Producto(6, R.drawable.chaqueta, "Chaqueta de cuero", "Diseño moderno, ideal para clima frío.", 599.000, 12, "Ropa"),
            Producto(7, R.drawable.camiseta , "Camiseta Oversize", "Estilo urbano, 100% algodón.", 120.000, 20, "Ropa"),
            Producto(8, R.drawable.pantalon, "Jeans Skinny Azul", "Corte ajustado, perfecto para el día a día.", 220.000, 18, "Ropa"),
            Producto(9, R.drawable.sudadera, "Sudadera con Capucha", "Con interior afelpado, ideal para invierno.", 180.500, 14, "Ropa"),
            Producto(10, R.drawable.zapatos, "Zapatos Deportivos ", "Con amortiguación Air para mayor comodidad.", 499.999, 9, "Ropa"),

            Producto(11, R.drawable.lampara, "Lámpara de Mesa LED", "Iluminación suave con puerto USB.", 89.999, 25, "Hogar"),
            Producto(12, R.drawable.sabanas, "Set de Sábanas Queen", "100% algodón con 4 piezas.", 139.500, 10, "Hogar"),
            Producto(13, R.drawable.silla, "Silla Ergonómica", "Ideal para home office, con soporte lumbar.", 350.00, 6, "Hogar"),
            Producto(14, R.drawable.cafetera, "Cafetera", "Para 12 tazas, con apagado automático.", 199.900, 8, "Hogar"),
            Producto(15, R.drawable.almohada, "Almohada", "Espuma con memoria, ideal para cervicales.", 79.000, 30, "Hogar"),

            Producto(16, R.drawable.balon, "Balón de Fútbol ", "Tamaño profesional, cosido a máquina.", 99.999, 15, "Deportes"),
            Producto(17, R.drawable.bicicleta, "Bicicleta ", "21 velocidades, suspensión delantera.", 899.000, 3, "Deportes"),

            Producto(18, R.drawable.mochila, "Mochila Antirrobo", "Compartimento para laptop, resistente al agua.", 150.009, 10, "Accesorios"),
            Producto(19, R.drawable.gorra, "Gorra Negra ", "Ajustable, con diseño bordado.", 59.999, 25, "Accesorios"),
            Producto(20, R.drawable.reloj, "Reloj Inteligente", "Monitor de ritmo cardíaco y pasos.", 289.000, 5, "Accesorios")
        )

        val adapter = ProductosAdapter(productos)
        recyclerView.adapter = adapter

        val btnIrAlCarrito = view.findViewById<FloatingActionButton>(R.id.btnIrCarrito)
        btnIrAlCarrito.setOnClickListener {
            findNavController().navigate(R.id.CarritoFragment)
        }

        return view
    }
}
