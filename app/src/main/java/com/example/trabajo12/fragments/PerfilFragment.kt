package com.example.trabajo12.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trabajo12.R

class PerfilFragment : Fragment() {

    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        sharedPref = requireActivity().getSharedPreferences("UsuarioPrefs", Context.MODE_PRIVATE)

        activity?.title = "Mi Perfil"

        val ivFotoPerfil = view.findViewById<ImageView>(R.id.ivFotoPerfil)
        val tvNombre = view.findViewById<TextView>(R.id.tvNombre)
        val tvEmail = view.findViewById<TextView>(R.id.tvEmail)
        val tvTelefono = view.findViewById<TextView>(R.id.tvTelefono)
        val tvContrasena = view.findViewById<TextView>(R.id.tvContrasena)
        val btnEditar = view.findViewById<Button>(R.id.btnEditarPerfil)

        val nombre = sharedPref.getString("nombre", "Usuario no registrado")
        val correo = sharedPref.getString("correo", "Correo no registrado")
        val telefono = sharedPref.getString("telefono", "Teléfono no registrado")
        val contrasena = sharedPref.getString("contrasena", "Contraseña no registrada")

        tvNombre.text = nombre
        tvEmail.text = correo
        tvTelefono.text = telefono
        tvContrasena.text = contrasena

        ivFotoPerfil.setImageResource(R.drawable.perfil)

        btnEditar.setOnClickListener {
            findNavController().navigate(R.id.action_editarperfilde_perfilFragment)
        }

        return view
    }
}