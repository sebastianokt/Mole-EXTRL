package com.example.trabajo12.Activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
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
    private lateinit var imageProfile: ImageView

    private var selectedImageUri: Uri? = null

    // Launcher para abrir galería
    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            selectedImageUri = result.data?.data
            imageProfile.setImageURI(selectedImageUri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        // Vincular vistas
        etNombre = findViewById(R.id.etNombre)
        etCorreo = findViewById(R.id.etCorreo)
        etTelefono = findViewById(R.id.etTelefono)
        etContrasena = findViewById(R.id.etContrasena)
        cbEsAdmin = findViewById(R.id.cbEsAdmin)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        tvnCancelar = findViewById(R.id.cancelar)
        imageProfile = findViewById(R.id.imageProfile)

        val sharedPref = getSharedPreferences("UsuarioPrefs", Context.MODE_PRIVATE)

        // Acciones
        imageProfile.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            pickImageLauncher.launch(intent)
        }

        btnRegistrar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val correo = etCorreo.text.toString()
            val telefono = etTelefono.text.toString()
            val contrasena = etContrasena.text.toString()
            val esAdmin = cbEsAdmin.isChecked

            if (nombre.isNotBlank() && correo.isNotBlank() && telefono.isNotBlank() && contrasena.isNotBlank()) {
                with(sharedPref.edit()) {
                    putString("nombre", nombre)
                    putString("correo", correo)
                    putString("telefono", telefono)
                    putString("contrasena", contrasena)
                    putBoolean("esAdmin", esAdmin)
                    selectedImageUri?.let {
                        putString("fotoPerfilUri", it.toString()) // Guardar URI si hay
                    }
                    apply()
                }

                Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, LoginActivity::class.java))
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
