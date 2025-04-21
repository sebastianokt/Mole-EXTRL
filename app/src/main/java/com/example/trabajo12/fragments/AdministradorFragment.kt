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
        // Inflamos el layout para este fragmento
        return inflater.inflate(R.layout.fragment_administrador, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Referenciamos los botones
        val btnAdminCuenta = view.findViewById<Button>(R.id.Adm_Cuenta)
        val btnAgregarProductos = view.findViewById<Button>(R.id.Adm_Agregar)
        val btnEliminarProductos = view.findViewById<Button>(R.id.Adm_Eliminar)

        // Navegar al fragmento de AdministrarCuentaFragment
        btnAdminCuenta.setOnClickListener {
            findNavController().navigate(R.id.Adm_Cuenta)
        }

        // Navegar al fragmento de AgregarProductosFragment
        btnAgregarProductos.setOnClickListener {
            findNavController().navigate(R.id.Adm_Agregar)
        }

        // Navegar al fragmento de EliminarProductosFragment
        btnEliminarProductos.setOnClickListener {
            findNavController().navigate(R.id.action_administradorFragment_to_eliminarProductosFragment)
        }
    }
}
