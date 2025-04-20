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

class AgregarProductoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_adm_agregar_productos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val nombre = view.findViewById<EditText>(R.id.etNombreProducto)
        val descripcion = view.findViewById<EditText>(R.id.etDescripcion)
        val precio = view.findViewById<EditText>(R.id.etPrecio)
        val cantidad = view.findViewById<EditText>(R.id.etCantidad)
        val btnAgregar = view.findViewById<Button>(R.id.btnAgregarProducto)

        btnAgregar.setOnClickListener {
            val nombreTexto = nombre.text.toString()
            val descripcionTexto = descripcion.text.toString()
            val precioValor = precio.text.toString().toDoubleOrNull()
            val cantidadValor = cantidad.text.toString().toIntOrNull()

            if (nombreTexto.isNotEmpty() && precioValor != null && cantidadValor != null) {
                Toast.makeText(requireContext(), "Producto agregado", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Completa todos los campos correctamente", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
