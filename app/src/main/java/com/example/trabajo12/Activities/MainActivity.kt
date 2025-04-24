package com.example.trabajo12.Activities

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import com.example.trabajo12.R
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private lateinit var drawerLayout: androidx.drawerlayout.widget.DrawerLayout
    private lateinit var drawerToggle: ActionBarDrawerToggle

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
        val navView: NavigationView = findViewById(R.id.nav_view)

        drawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.InicioFragment,
                R.id.categoriasFragment,
                R.id.ProductosFragment,
                R.id.CarritoFragment,
                R.id.PerfilFragment,
                R.id.TiendasCercanasFragment,
                R.id.cerrar_sesion
        ),
            drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.cerrar_sesion -> {
                    logout()
                    drawerLayout.closeDrawers()
                    true
                }
                else -> {
                    navController.navigate(menuItem.itemId)
                    drawerLayout.closeDrawers()
                    true
                }
            }
        }

        val esAdmin = intent.getBooleanExtra("esAdmin", false)

        if (esAdmin) {
            navController.navigate(R.id.administradorFragment)
        }
    }
    private fun logout() {
        val sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("is_logged_in", false)
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
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
