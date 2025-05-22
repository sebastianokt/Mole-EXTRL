package com.example.trabajo12.fragments

import CuentaAdapter
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo12.R
import com.example.trabajo12.models.Cuenta
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AdministrarCuentaFragment : Fragment() {

    private lateinit var cuentas: MutableList<Cuenta>
    private lateinit var adapter: CuentaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_adm_cuenta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCuentas)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        cuentas = cargarCuentas()
        adapter = CuentaAdapter(cuentas)
        recyclerView.adapter = adapter

        val btnAgregar = view.findViewById<FloatingActionButton>(R.id.AgregarCuenta)
        btnAgregar.setOnClickListener {
            mostrarDialogoAgregarCuenta()
        }
    }

    private fun mostrarDialogoAgregarCuenta() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_agregar_cuenta, null)
        val etNombre = dialogView.findViewById<EditText>(R.id.editNombre)
        val etCorreo = dialogView.findViewById<EditText>(R.id.editCorreo)
        val etCedula = dialogView.findViewById<EditText>(R.id.editCedula)
        val etCelular = dialogView.findViewById<EditText>(R.id.editCelular)

        AlertDialog.Builder(requireContext())
            .setTitle("Agregar Cuenta")
            .setView(dialogView)
            .setPositiveButton("Guardar") { _, _ ->
                val cuenta = Cuenta(
                    etNombre.text.toString(),
                    etCorreo.text.toString(),
                    etCedula.text.toString(),
                    etCelular.text.toString()
                )
                guardarCuenta(cuenta)
                cuentas.add(cuenta)
                adapter.notifyItemInserted(cuentas.size - 1)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun guardarCuenta(cuenta: Cuenta) {
        val prefs = requireContext().getSharedPreferences("CuentasPrefs", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        val lista = prefs.getStringSet("cuentas", mutableSetOf())?.toMutableSet() ?: mutableSetOf()
        lista.add("${cuenta.nombre}|${cuenta.correo}|${cuenta.cedula}|${cuenta.celular}")
        editor.putStringSet("cuentas", lista)
        editor.apply()
    }

    private fun cargarCuentas(): MutableList<Cuenta> {
        val prefs = requireContext().getSharedPreferences("CuentasPrefs", Context.MODE_PRIVATE)
        val lista = prefs.getStringSet("cuentas", emptySet()) ?: emptySet()
        return lista.mapNotNull {
            val partes = it.split("|")
            if (partes.size == 4) Cuenta(partes[0], partes[1], partes[2], partes[3]) else null
        }.toMutableList()
    }
}
