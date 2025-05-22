package com.example.trabajo12.fragments

import CuentaAdapter
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
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
    private lateinit var sharedPreferences: SharedPreferences
    private val PREFS_NAME = "CuentasPrefs"
    private val CUENTAS_COUNT_KEY = "cuentas_count"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_adm_cuenta, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCuentas)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        cuentas = cargarCuentas()
        adapter = CuentaAdapter(cuentas) { position ->
            mostrarDialogoConfirmacionEliminar(position)
        }
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
                    nombre = etNombre.text.toString(),
                    correo = etCorreo.text.toString(),
                    cedula = etCedula.text.toString(),
                    celular = etCelular.text.toString()
                )
                guardarCuenta(cuenta)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun mostrarDialogoConfirmacionEliminar(position: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle("Confirmar eliminación")
            .setMessage("¿Estás seguro de que deseas eliminar esta cuenta?")
            .setPositiveButton("Eliminar") { _, _ ->
                eliminarCuenta(position)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun cargarCuentas(): MutableList<Cuenta> {
        val cuentasList = mutableListOf<Cuenta>()
        val count = sharedPreferences.getInt(CUENTAS_COUNT_KEY, 0)

        for (i in 0 until count) {
            val nombre = sharedPreferences.getString("cuenta_${i}_nombre", "") ?: ""
            val correo = sharedPreferences.getString("cuenta_${i}_correo", "") ?: ""
            val cedula = sharedPreferences.getString("cuenta_${i}_cedula", "") ?: ""
            val celular = sharedPreferences.getString("cuenta_${i}_celular", "") ?: ""

            if (nombre.isNotEmpty() || correo.isNotEmpty() || cedula.isNotEmpty() || celular.isNotEmpty()) {
                cuentasList.add(Cuenta(nombre, correo, cedula, celular))
            }
        }

        return cuentasList
    }

    private fun guardarCuenta(nuevaCuenta: Cuenta) {
        cuentas.add(nuevaCuenta)
        actualizarSharedPreferences()
        adapter.updateCuentas(cuentas)
    }

    private fun eliminarCuenta(position: Int) {
        cuentas.removeAt(position)
        actualizarSharedPreferences()
        adapter.updateCuentas(cuentas)
    }

    private fun actualizarSharedPreferences() {
        val editor = sharedPreferences.edit()

        // Limpiar todas las cuentas existentes
        val count = sharedPreferences.getInt(CUENTAS_COUNT_KEY, 0)
        for (i in 0 until count) {
            editor.remove("cuenta_${i}_nombre")
            editor.remove("cuenta_${i}_correo")
            editor.remove("cuenta_${i}_cedula")
            editor.remove("cuenta_${i}_celular")
        }

        // Guardar las cuentas actualizadas
        for ((index, cuenta) in cuentas.withIndex()) {
            editor.putString("cuenta_${index}_nombre", cuenta.nombre)
            editor.putString("cuenta_${index}_correo", cuenta.correo)
            editor.putString("cuenta_${index}_cedula", cuenta.cedula)
            editor.putString("cuenta_${index}_celular", cuenta.celular)
        }

        // Actualizar el contador
        editor.putInt(CUENTAS_COUNT_KEY, cuentas.size)
        editor.apply()
    }
}