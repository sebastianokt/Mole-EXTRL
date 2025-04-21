package com.example.trabajo12.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.trabajo12.R

class AgregarProductosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adm_agregar_productos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etNombreProducto = view.findViewById<EditText>(R.id.etNombreProducto)
        val etPrecioProducto = view.findViewById<EditText>(R.id.etPrecioProducto)
        val btnGuardarProducto = view.findViewById<Button>(R.id.btnGuardarProducto)

        btnGuardarProducto.setOnClickListener {
            val nombre = etNombreProducto.text.toString().trim()
            val precio = etPrecioProducto.text.toString().trim()

            if (nombre.isNotEmpty() && precio.isNotEmpty()) {
                val sharedPreferences = requireActivity().getSharedPreferences("ProductosPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("nombreProducto", nombre)
                editor.putString("precioProducto", precio)
                editor.apply()

                Toast.makeText(requireContext(), "Producto guardado correctamente", Toast.LENGTH_SHORT).show()

                etNombreProducto.text.clear()
                etPrecioProducto.text.clear()
            } else {
                Toast.makeText(requireContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
