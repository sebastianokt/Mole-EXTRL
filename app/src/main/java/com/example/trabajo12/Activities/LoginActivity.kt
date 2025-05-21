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
    private lateinit var btGoole: Button
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 123
    private val TAG = "GoogleSignIn"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //  Obtener referencias de los campos y botones
        val editTextUsername = findViewById<EditText>(R.id.etCorreo)
        val editTextPassword = findViewById<EditText>(R.id.etContrasena)
        val buttonIngresar = findViewById<Button>(R.id.btnLogin)
        val textViewRecuperar = findViewById<TextView>(R.id.recuperarcontrasena)
        val textViewRegistrate = findViewById<TextView>(R.id.tvRegistrate) // Assuming you have a TextView with this ID for "Regístrate"
        val textViewCancelar = findViewById<TextView>(R.id.tvCancelar)

        // Recuperar datos guardados en SharedPreferences
        val sharedPreferences: SharedPreferences =
            getSharedPreferences("UsuarioPrefs", MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("correo", "") ?: ""
        val savedPassword = sharedPreferences.getString("contrasena", "") ?: ""

        Log.d(
            "LoginActivity",
            "Datos guardados -> Nombre: $savedUsername, Contraseña: $savedPassword"
        )

        buttonIngresar.setOnClickListener {
            val username = editTextUsername.text.toString().trim()
            val password = editTextPassword.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Log.d("LoginActivity", "Intento de inicio -> correo: $username, Contraseña: $password")

            if (username == savedUsername && password == savedPassword) {
                val esAdmin = sharedPreferences.getBoolean("esAdmin", false)

                if (esAdmin) {
                    Toast.makeText(this, "Inicio de sesión exitoso como administrador", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                }

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("esAdmin", esAdmin)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }


        textViewRegistrate.setOnClickListener {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        textViewCancelar.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        textViewRecuperar.setOnClickListener {
            startActivity(Intent(this, RecuperarContrasena::class.java))
        }
        // 👉 Configurar Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()

        // Crear el cliente de Google SignIn
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        btGoole= findViewById(R.id.btGoole)

        btGoole.setOnClickListener {
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

            // Inicio de sesión exitoso
            Log.d(TAG, "signInSuccess: ${account.email}")
            Toast.makeText(this, "Bienvenido ${account.displayName}", Toast.LENGTH_SHORT).show()

            // Ir a MainActivity
            intent = Intent(this, MainActivity::class.java)
            intent.putExtra("USER_EMAIL", account.email)
            intent.putExtra("USER_NAME", account.displayName)
            startActivity(intent)

        } catch (e: ApiException) {
            // Error en el inicio de sesión


            Toast.makeText(this, "mensaje", Toast.LENGTH_SHORT).show()
        }
    }
}