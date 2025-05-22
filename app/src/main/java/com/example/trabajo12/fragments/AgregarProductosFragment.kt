package com.example.trabajo12.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.trabajo12.R
import com.example.trabajo12.models.Producto
import com.example.trabajo12.models.ProductosData
import java.util.*

class AgregarProductosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_adm_agregar_productos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etNombreProducto = view.findViewById<EditText>(R.id.etNombreProducto)
        val etDescripcion = view.findViewById<EditText>(R.id.edtDescripcion)
        val etPrecioProducto = view.findViewById<EditText>(R.id.etPrecioProducto)
        val etCategoria = view.findViewById<EditText>(R.id.etCategoria)
        val etCantidad = view.findViewById<EditText>(R.id.etCantidad)
        val btnGuardarProducto = view.findViewById<Button>(R.id.btnGuardarProducto)

        btnGuardarProducto.setOnClickListener {
            val nombre = etNombreProducto.text.toString().trim()
            val descripcion = etDescripcion.text.toString().trim()
            val precioStr = etPrecioProducto.text.toString().trim()
            val categoria = etCategoria.text.toString().trim()
            val cantidadStr = etCantidad.text.toString().trim()

            if (nombre.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty() || categoria.isEmpty() || cantidadStr.isEmpty()) {
                Toast.makeText(requireContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val precio = precioStr.toDouble()
                val cantidad = cantidadStr.toInt()

                val nuevoId = ProductosData.productos.maxByOrNull { it.id }?.id?.plus(1) ?: 1

                val nuevoProducto = Producto(
                    id = nuevoId,
                    imagen = R.drawable.perfil,
                    nombre = nombre,
                    descripcion = descripcion,
                    precio = precio,
                    cantidad = cantidad,
                    categoria = categoria
                )

                ProductosData.productos.add(nuevoProducto)

                etNombreProducto.text.clear()
                etDescripcion.text.clear()
                etPrecioProducto.text.clear()
                etCategoria.text.clear()
                etCantidad.text.clear()

                Toast.makeText(
                    requireContext(),
                    "Producto agregado exitosamente",
                    Toast.LENGTH_SHORT
                ).show()

            } catch (e: NumberFormatException) {
                Toast.makeText(
                    requireContext(),
                    "Precio y cantidad deben ser números válidos",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}