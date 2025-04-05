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
class ElectronicaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_productos, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewProductos)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val productos = listOf(
            Producto(1, R.drawable.celular, "Smartphone Galaxy A34", "Pantalla AMOLED, cámara triple y batería de larga duración.", 999.999, 10, "Electrónica"),
            Producto(2, R.drawable.lenovo, "Laptop Lenovo i5", "Laptop de alto rendimiento con 16GB RAM y SSD 512GB.", 2199.500, 7, "Electrónica"),
            Producto(3, R.drawable.jbg, "Auriculares Inalámbricos JBL", "Calidad de sonido premium con cancelación de ruido.", 349.999, 15, "Electrónica"),
            Producto(4, R.drawable.tv, "Smart TV 50\"", "Televisor inteligente 4K con Android TV integrado.", 1890.000, 5, "Electrónica"),
            Producto(5, R.drawable.gpro, "Cámara Deportiva GoPro", "Resistente al agua y con grabación en 4K.", 1299.999, 8, "Electrónica")
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
