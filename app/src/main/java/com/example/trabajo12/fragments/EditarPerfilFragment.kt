package com.example.trabajo12.fragments

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trabajo12.R

class EditarPerfilFragment : Fragment() {

    private lateinit var sharedPref: SharedPreferences
    private lateinit var ivFotoPerfil: ImageView
    private var uriImagenSeleccionada: Uri? = null

    // Lanza el selector de imágenes
    private val seleccionarImagenLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            uriImagenSeleccionada = it
            ivFotoPerfil.setImageURI(it)

            // Guardar la URI en SharedPreferences
            with(sharedPref.edit()) {
                putString("fotoPerfilUri", it.toString())
                apply()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_editar_perfil, container, false)

        sharedPref = requireActivity().getSharedPreferences("UsuarioPrefs", Context.MODE_PRIVATE)

        activity?.title = "Editar Perfil"

        ivFotoPerfil = view.findViewById(R.id.ivFotoPerfilEditar)
        val etNombre = view.findViewById<EditText>(R.id.etNombre)
        val etEmail = view.findViewById<EditText>(R.id.etEmail)
        val etTelefono = view.findViewById<EditText>(R.id.etTelefono)
        val etContrasena = view.findViewById<EditText>(R.id.etContrasena)
        val btnCambiarFoto = view.findViewById<Button>(R.id.btnCambiarFoto)
        val btnGuardar = view.findViewById<Button>(R.id.btnGuardarCambios)

        // Cargar foto guardada si existe
        val fotoUriGuardada = sharedPref.getString("fotoPerfilUri", null)
        if (fotoUriGuardada != null) {
            ivFotoPerfil.setImageURI(Uri.parse(fotoUriGuardada))
        } else {
            ivFotoPerfil.setImageResource(R.drawable.perfil) // imagen por defecto
        }

        // Cargar datos existentes
        etNombre.setText(sharedPref.getString("nombre", ""))
        etEmail.setText(sharedPref.getString("correo", ""))
        etTelefono.setText(sharedPref.getString("telefono", ""))
        etContrasena.setText(sharedPref.getString("contrasena", ""))

        btnCambiarFoto.setOnClickListener {
            seleccionarImagenLauncher.launch("image/*")
        }

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val email = etEmail.text.toString()
            val telefono = etTelefono.text.toString()
            val contrasena = etContrasena.text.toString()

            if (nombre.isNotEmpty() && email.isNotEmpty() && telefono.isNotEmpty() && contrasena.isNotEmpty()) {
                guardarCambios(nombre, email, telefono, contrasena)
                findNavController().navigate(R.id.action_editarperfilde_perfilFragment2)
            } else {
                Toast.makeText(context, "¡Complete todos los campos!", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun guardarCambios(nombre: String, email: String, telefono: String, contrasena: String) {
        with(sharedPref.edit()) {
            putString("nombre", nombre)
            putString("correo", email)
            putString("telefono", telefono)
            putString("contrasena", contrasena)
            apply()
        }
        Toast.makeText(context, "Perfil actualizado correctamente", Toast.LENGTH_SHORT).show()
    }
}
