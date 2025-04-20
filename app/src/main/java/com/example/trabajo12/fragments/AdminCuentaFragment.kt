package com.example.trabajo12.fragments
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trabajo12.R

class AdminGeneralFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_adm_cuenta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Referencia a los TextView del layout
        val txtNombreCuenta = view.findViewById<TextView>(R.id.txtNombreCuenta)
        val txtCorreoCuenta = view.findViewById<TextView>(R.id.txtCorreoCuenta)

        // Obtener SharedPreferences
        val sharedPreferences = requireActivity().getSharedPreferences("UsuarioPrefs", Context.MODE_PRIVATE)
        val savedNombre = sharedPreferences.getString("nombre", "Nombre no disponible")
        val savedCorreo = sharedPreferences.getString("correo", "Correo no disponible")

        // Mostrar los datos en los TextView
        txtNombreCuenta.text = savedNombre
        txtCorreoCuenta.text = savedCorreo

        // Botón para ir a la sección de administración de cuentas
        val btnAdminCuenta = view.findViewById<Button>(R.id.Adm_Cuenta)
        btnAdminCuenta.setOnClickListener {
            findNavController().navigate(R.id.action_adminGeneralFragment_to_adminCuentaFragment)
        }
    }
}
