package com.example.trabajo12.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo12.R
import com.example.trabajo12.adapters.CategoriaAdapter
import com.example.trabajo12.models.Categoria

class CategoriasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_categorias, container, false)

        activity?.title = "Categorías"

        val recyclerView = view.findViewById<RecyclerView>(R.id.categoriasFragment)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val categorias = listOf(
            Categoria(R.drawable.gpro, "Electrónica", 5),
            Categoria(R.drawable.sudadera, "Ropa", 5),
            Categoria(R.drawable.lampara, "Hogar", 5),
            Categoria(R.drawable.balon, "Deportes", 2),
            Categoria(R.drawable.mochila, "Accesorios", 3)
        )

        val adapter = CategoriaAdapter(categorias) { categoria ->
            when (categoria.nombre) {
                "Ropa" -> findNavController().navigate(R.id.action_categoriasFragment_to_ropaFragment)
                "Hogar" -> findNavController().navigate(R.id.action_categoriasFragment_to_hogarFragment)
                "Deportes" -> findNavController().navigate(R.id.action_categoriasFragment_to_deportesFragment)
                "Accesorios" -> findNavController().navigate(R.id.action_categoriasFragment_to_accesoriosFragment)
            }
        }

        recyclerView.adapter = adapter

        return view
    }
}