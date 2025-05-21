package com.example.trabajo12.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.trabajo12.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class TiendascercanasFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val ubicacionBogota = LatLng(4.6097, -74.0817)  // Bogotá
    private val ubicacionChapinero = LatLng(4.6462, -74.0630) //chapinero

    private val ubicacionSuba = LatLng(4.7480, -74.1003)//suba


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tiendas_cercanas, container, false)

        // Insertar el mapa dinámicamente en el contenedor
        val mapFragment = SupportMapFragment.newInstance()
        childFragmentManager.beginTransaction()
            .replace(R.id.map, mapFragment)
            .commit()

        mapFragment.getMapAsync(this)

        // Configurar botones
        view.findViewById<Button>(R.id.btnLocation1).setOnClickListener {
            moveToLocation(ubicacionChapinero, "Chapinero")
        }

        view.findViewById<Button>(R.id.btnLocation2).setOnClickListener {
            moveToLocation(ubicacionSuba, "Suba")
        }

        return view
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true

        // Mostrar Bogotá por defecto
        moveToLocation(ubicacionBogota, "Bogotá")
    }

    private fun moveToLocation(location: LatLng, title: String) {
        mMap.clear()
        mMap.addMarker(MarkerOptions().position(location).title(title))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12f))
    }
}
