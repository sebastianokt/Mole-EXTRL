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
import com.example.trabajo12.fragments.HomeActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class LoginActivity : AppCompatActivity() {

    private lateinit var btGoogle: Button
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 123
    private val TAG = "GoogleSignIn"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Referencias a campos de texto y botones
        val editTextUsername = findViewById<EditText>(R.id.etCorreo)
        val editTextPassword = findViewById<EditText>(R.id.etContrasena)
        val buttonIngresar = findViewById<Button>(R.id.btnLogin)
        val textViewRecuperar = findViewById<TextView>(R.id.recuperarcontrasena)
        val textViewRegistrate = findViewById<TextView>(R.id.tvRegistrate)
        val textViewCancelar = findViewById<TextView>(R.id.tvCancelar)
        btGoogle = findViewById(R.id.btGoole)

        // Recuperar datos guardados en SharedPreferences
        val sharedPreferences: SharedPreferences = getSharedPreferences("UsuarioPrefs", MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("correo", "") ?: ""
        val savedPassword = sharedPreferences.getString("contrasena", "") ?: ""

        Log.d(TAG, "Datos guardados -> Correo: $savedUsername, Contraseña: $savedPassword")

        // Botón Ingresar (correo y contraseña)
        buttonIngresar.setOnClickListener {
            val username = editTextUsername.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (username == savedUsername && password == savedPassword) {
                val esAdmin = sharedPreferences.getBoolean("esAdmin", false)
                val mensaje = if (esAdmin) "Inicio de sesión exitoso como administrador"
                else "Inicio de sesión exitoso"

                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("esAdmin", esAdmin)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón Registrarse
        textViewRegistrate.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        // Botón Cancelar → volver a Home
        textViewCancelar.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        // Recuperar contraseña
        textViewRecuperar.setOnClickListener {
            startActivity(Intent(this, RecuperarContrasena::class.java))
        }

        // Configurar Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        btGoogle.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            val email = account.email
            val name = account.displayName

            // Guardar datos en SharedPreferences
            val sharedPreferences: SharedPreferences = getSharedPreferences("UsuarioPrefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("correo", email)
            editor.putString("nombre", name)
            editor.putBoolean("esAdmin", false) // Por defecto, usuario normal
            editor.apply()

            // Mostrar mensaje de bienvenida y sugerencia
            Toast.makeText(this, "Bienvenido $name. Por favor completa tus datos.", Toast.LENGTH_LONG).show()

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("USER_EMAIL", email)
            intent.putExtra("USER_NAME", name)
            startActivity(intent)
            finish()

        } catch (e: ApiException) {
            Log.e(TAG, "Error en el inicio de sesión con Google", e)
            Toast.makeText(this, "Error al iniciar sesión con Google", Toast.LENGTH_SHORT).show()
        }
    }
}
