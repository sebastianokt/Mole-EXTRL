package com.example.trabajo12.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.trabajo12.R

class AdministrarCuentaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adm_cuenta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txtNombre = view.findViewById<TextView>(R.id.txtNombreCuenta)
        val txtCorreo = view.findViewById<TextView>(R.id.txtCorreoCuenta)

        val sharedPreferences = requireActivity().getSharedPreferences("UsuarioPrefs", Context.MODE_PRIVATE)


    }
}
