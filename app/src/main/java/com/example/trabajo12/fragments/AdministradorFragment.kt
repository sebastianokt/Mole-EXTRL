package com.example.trabajo12.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trabajo12.R

class AdministradorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_administrador, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val btnAdminCuenta = view.findViewById<Button>(R.id.Adm_Cuenta)
        val btnAgregarProductos = view.findViewById<Button>(R.id.Adm_Agregar)
        val btnEliminarProductos = view.findViewById<Button>(R.id.Adm_Eliminar)
        val btnEditarProductos = view.findViewById<Button>(R.id.Adm_Editar)
        val btnStock = view.findViewById<Button>(R.id.Adm_Stock)

        // Navegar a Administrar Cuenta
        btnAdminCuenta.setOnClickListener {
            findNavController().navigate(R.id.action_administradorFragment_to_administrarCuentaFragment)
        }

        // Navegar a Agregar Productos
        btnAgregarProductos.setOnClickListener {
            findNavController().navigate(R.id.action_administradorFragment_to_agregarProductosFragment)
        }

        // Navegar a Eliminar Productos
        btnEliminarProductos.setOnClickListener {
            findNavController().navigate(R.id.action_administradorFragment_to_eliminarProductosFragment)
        }

        // Navegar a Editar Productos
        btnEditarProductos.setOnClickListener {
            findNavController().navigate(R.id.action_administradorFragment_to_editarProductosFragment)
        }

        // Navegar a Stock en Tienda
        btnStock.setOnClickListener {
            findNavController().navigate(R.id.action_administradorFragment_to_stockFragment)
        }
    }
}
