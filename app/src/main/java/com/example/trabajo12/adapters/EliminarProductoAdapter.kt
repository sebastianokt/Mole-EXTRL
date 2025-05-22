package com.example.trabajo12.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo12.R
import com.example.trabajo12.models.Producto

class EliminarProductoAdapter(
    private val productos: MutableList<Producto>,
    private val onEliminarClick: (Producto) -> Unit
) : RecyclerView.Adapter<EliminarProductoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagen: ImageView = view.findViewById(R.id.imgProducto)
        val nombre: TextView = view.findViewById(R.id.txtNombre)
        val descripcion: TextView = view.findViewById(R.id.txtDescripcion)
        val btnEliminar: Button = view.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_eliminar, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = productos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = productos[position]
        holder.imagen.setImageResource(producto.imagen)
        holder.nombre.text = producto.nombre
        holder.descripcion.text = producto.descripcion
        holder.btnEliminar.setOnClickListener {
            onEliminarClick(producto)
        }
    }
}