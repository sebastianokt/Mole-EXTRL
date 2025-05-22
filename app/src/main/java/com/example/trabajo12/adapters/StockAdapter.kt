package com.example.trabajo12.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo12.R
import com.example.trabajo12.models.Producto

class StockAdapter(private val productos: MutableList<Producto>) :
    RecyclerView.Adapter<StockAdapter.StockViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto_stock, parent, false)
        return StockViewHolder(view)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val producto = productos[position]
        holder.bind(producto)
    }

    override fun getItemCount(): Int = productos.size

    class StockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgProducto: ImageView = itemView.findViewById(R.id.imgProducto)
        private val txtNombre: TextView = itemView.findViewById(R.id.txtNombre)
        private val txtDescripcion: TextView = itemView.findViewById(R.id.txtDescripcion)
        private val txtPrecio: TextView = itemView.findViewById(R.id.txtPrecio)
        private val txtCantidad: TextView = itemView.findViewById(R.id.txtCantidad)

        fun bind(producto: Producto) {
            imgProducto.setImageResource(producto.imagen)
            txtNombre.text = producto.nombre
            txtDescripcion.text = producto.descripcion
            txtPrecio.text = "$${producto.precio}"
            txtCantidad.text = "Disponibles: ${producto.cantidad}"
        }
    }
}