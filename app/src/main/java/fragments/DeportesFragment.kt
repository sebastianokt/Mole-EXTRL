package fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import adapters.ProductosAdapter
import com.example.trabajo12.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import models.Producto
class DeportesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_productos, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProductos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val productos = listOf(
            Producto(16, R.drawable.balon, "Balón de Fútbol ", "Tamaño profesional, cosido a máquina.", 99.999, 15, "Deportes"),
            Producto(17, R.drawable.bicicleta, "Bicicleta ", "21 velocidades, suspensión delantera.", 899.000, 3, "Deportes")
        )

        val adapter = ProductosAdapter(productos)
        recyclerView.adapter = adapter

        val btnIrAlCarrito = view.findViewById<FloatingActionButton>(R.id.btnIrCarrito)
        btnIrAlCarrito?.setOnClickListener {
            findNavController().navigate(R.id.carritoFragment)
        }

        return view
    }
}
