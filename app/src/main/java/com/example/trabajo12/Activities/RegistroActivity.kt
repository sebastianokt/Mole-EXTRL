package com.example.trabajo12.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.trabajo12.R
import com.example.trabajo12.fragments.HomeActivity

class RegistroActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etTelefono: EditText
    private lateinit var etContrasena: EditText
    private lateinit var cbEsAdmin: CheckBox
    private lateinit var btnRegistrar: Button
    private lateinit var tvnCancelar: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        etNombre = findViewById(R.id.etNombre)
        etCorreo = findViewById(R.id.etCorreo)
        etTelefono = findViewById(R.id.etTelefono)
        etContrasena = findViewById(R.id.etContrasena)
        cbEsAdmin = findViewById(R.id.cbEsAdmin)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        tvnCancelar = findViewById(R.id.cancelar)

        val sharedPref = getSharedPreferences("UsuarioPrefs", Context.MODE_PRIVATE)

        btnRegistrar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val correo = etCorreo.text.toString()
            val telefono = etTelefono.text.toString()
            val contrasena = etContrasena.text.toString()

            if (nombre.isNotBlank() && correo.isNotBlank() && telefono.isNotBlank() && contrasena.isNotBlank()) {
                sharedPref.edit().apply {
                    putString("nombre", nombre)
                    putString("correo", correo)
                    putString("telefono", telefono)
                    putString("contrasena", contrasena)
                    putBoolean("esAdmin", cbEsAdmin.isChecked)  // <-- Marca si es admin
                    apply()
                }

                Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java)) // Suponiendo que vuelves al login
                finish()
            } else {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
        tvnCancelar.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
        }
    }