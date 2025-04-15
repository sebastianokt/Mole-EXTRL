package com.example.trabajo12.Activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabajo12.R
import com.example.trabajo12.fragments.InicioFragment
import com.example.trabajo12.fragments.PerfilFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //  Obtener referencias de los campos y botones
        val editTextUsername = findViewById<EditText>(R.id.etCorreo)
        val editTextPassword = findViewById<EditText>(R.id.etContrasena)
        val buttonIngresar = findViewById<Button>(R.id.btnLogin)
        val textViewRecuperar = findViewById<TextView>(R.id.recuperarcontrasena)
        val textViewRegistrate = findViewById<TextView>(R.id.tvIrARegistro)
        val buttonGoogle = findViewById<Button>(R.id.bt_inrGoogle)

        // Recuperar datos guardados en SharedPreferences
        val sharedPreferences: SharedPreferences = getSharedPreferences("UsuarioPrefs", MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("correo", "") ?: ""
        val savedPassword = sharedPreferences.getString("contrasena", "") ?: ""

        Log.d("LoginActivity", "Datos guardados -> Nombre: $savedUsername, Contraseña: $savedPassword")

        buttonIngresar.setOnClickListener {
            val username = editTextUsername.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Log.d("LoginActivity", "Intento de inicio -> correo: $username, Contraseña: $password")

            if (username == savedUsername && password == savedPassword) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        textViewRegistrate.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        textViewRecuperar.setOnClickListener {
            startActivity(Intent(this, RecuperarContrasena::class.java))
        }


    }
}
