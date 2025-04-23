package com.example.trabajo12.Activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.trabajo12.R

class Home_Activity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Vincular botones con su ID
        val buttonComienza = findViewById<Button>(R.id.buttonComienza1)
        val buttonRegistro = findViewById<TextView>(R.id.registrarHome)
        val textViewAdmin = findViewById<TextView>(R.id.textViewAdmin) // Asegúrate de tener este TextView en tu XML

        buttonComienza.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        buttonRegistro.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        // Ir al administrador
        textViewAdmin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("openAdmin", true)
            startActivity(intent)
        }
    }
}
