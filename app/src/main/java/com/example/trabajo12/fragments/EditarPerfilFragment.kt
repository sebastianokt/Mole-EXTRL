package com.example.trabajo12.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trabajo12.R

class EditarPerfilFragment : Fragment() {

    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_editar_perfil, container, false)

        sharedPref = requireActivity().getSharedPreferences("UsuarioPrefs", Context.MODE_PRIVATE)

        activity?.title = "Editar Perfil"

        val ivFotoPerfil = view.findViewById<ImageView>(R.id.ivFotoPerfilEditar)
        val etNombre = view.findViewById<EditText>(R.id.etNombre)
        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val etTelefono = view.findViewById<EditText>(R.id.etTelefono)
        val btnCambiarFoto = view.findViewById<Button>(R.id.btnCambiarFoto)
        val btnGuardar = view.findViewById<Button>(R.id.btnGuardarCambios)

        etNombre.setText(sharedPref.getString("nombre", ""))
        etEmail.setText(sharedPref.getString("correo", ""))
        etTelefono.setText(sharedPref.getString("telefono", ""))

        ivFotoPerfil.setImageResource(R.drawable.perfil)

        btnCambiarFoto.setOnClickListener {
            Toast.makeText(context, ")",
                Toast.LENGTH_SHORT).show()
        }

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val email = etEmail.text.toString()
            val telefono = etTelefono.text.toString()

            if (nombre.isNotEmpty() && email.isNotEmpty() && telefono.isNotEmpty()) {
                guardarCambios(nombre, email, telefono)
                findNavController().navigate(R.id.action_editarPerfilFragment_to_perfilFragment2)
            } else {
                Toast.makeText(context, "¡Complete todos los campos!", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun guardarCambios(nombre: String, email: String, telefono: String) {
        with(sharedPref.edit()) {
            putString("nombre", nombre)
            putString("correo", email)
            putString("telefono", telefono)
            apply() // Guardar cambios
        }
        Toast.makeText(context, "Perfil actualizado correctamente", Toast.LENGTH_SHORT).show()
    }
}