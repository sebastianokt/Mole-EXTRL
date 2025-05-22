package com.example.trabajo12.Activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.ui.navigateUp
import com.example.trabajo12.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var drawerLayout: androidx.drawerlayout.widget.DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuración de la toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Configuración del NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        navController = navHostFragment?.findNavController()!!

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val esAdmin = intent.getBooleanExtra("esAdmin", false)

        appBarConfiguration = if (esAdmin) {
            AppBarConfiguration(
                setOf(
                    R.id.InicioFragment,
                    R.id.categoriasFragment,
                    R.id.ProductosFragment,
                    R.id.CarritoFragment,
                    R.id.PerfilFragment,
                    R.id.TiendasCercanasFragment,
                    R.id.administradorFragment
                ),
                drawerLayout
            )
        } else {
            AppBarConfiguration(
                setOf(
                    R.id.InicioFragment,
                    R.id.categoriasFragment,
                    R.id.ProductosFragment,
                    R.id.CarritoFragment,
                    R.id.PerfilFragment,
                    R.id.TiendasCercanasFragment
                ),
                drawerLayout
            )
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.menu.findItem(R.id.administradorFragment).isVisible = esAdmin

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.cerrar_sesion -> {
                    logout()
                    drawerLayout.closeDrawers()
                    true
                }
                else -> {
                    menuItem.isChecked = true
                    drawerLayout.closeDrawers()
                    navController.navigate(menuItem.itemId)
                    true
                }
            }
        }

    }

    private fun logout() {
        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("is_logged_in", false)
            putBoolean("es_admin", false)
            apply()
        }

        navController.navigate(
            R.id.loginActivity,
            null,
            NavOptions.Builder()
                .setPopUpTo(R.id.InicioFragment, true)
                .build()
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}