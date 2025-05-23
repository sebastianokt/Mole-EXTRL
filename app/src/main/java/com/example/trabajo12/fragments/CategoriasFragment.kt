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
            Categoria(R.drawable.camiseta, "Camisas", 5),
            Categoria(R.drawable.sudadera, "Chaquetas", 5),
            Categoria(R.drawable.pantalon, "Pantalones", 5),
            Categoria(R.drawable.zapatos, "Zapatos", 2),
            Categoria(R.drawable.vestidos, "Vestidos", 3)
        )

        val adapter = CategoriaAdapter(categorias) { categoria ->
            when (categoria.nombre) {
                "Camisas" -> findNavController().navigate(R.id.action_categoriasFragment_to_ropaFragment)
                "Chaquetas" -> findNavController().navigate(R.id.action_categoriasFragment_to_electroFragment)
                "Pantalones" -> findNavController().navigate(R.id.action_categoriasFragment_to_hogarFragment)
                "Zapatos" -> findNavController().navigate(R.id.action_categoriasFragment_to_deportesFragment)
                "Vestidos" -> findNavController().navigate(R.id.action_categoriasFragment_to_accesoriosFragment)
            }
        }

        recyclerView.adapter = adapter

        return view
    }
}