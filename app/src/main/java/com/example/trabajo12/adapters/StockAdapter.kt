package com.example.trabajo12.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo12.R
import com.example.trabajo12.models.Producto

class StockAdapter(
    private val listaProductos: List<Producto>
) : RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    inner class StockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProducto: ImageView = itemView.findViewById(R.id.imgProducto)
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombre)
        val txtCantidad: TextView = itemView.findViewById(R.id.txtCantidad)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return StockViewHolder(view)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val producto = listaProductos[position]
        holder.imgProducto.setImageResource(producto.imagen)
        holder.txtNombre.text = producto.nombre
        holder.txtCantidad.text = "Cantidad: ${producto.cantidad}"
    }

    override fun getItemCount(): Int = listaProductos.size
}
