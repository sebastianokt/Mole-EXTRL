package com.example.trabajo12.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo12.R
import com.example.trabajo12.models.Producto
import com.example.trabajo12.models.Carrito

class ProductosAdapter(
    private val productos: List<Producto>
) : RecyclerView.Adapter<ProductosAdapter.ProductoViewHolder>() {

    inner class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProducto: ImageView = itemView.findViewById(R.id.imgProducto)
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombre)
        val txtDescripcion: TextView = itemView.findViewById(R.id.txtDescripcion)
        val txtPrecio: TextView = itemView.findViewById(R.id.txtPrecio)
        val txtCantidadDisponible: TextView = itemView.findViewById(R.id.txtCantidad)
        val txtCantidadSeleccionada: TextView = itemView.findViewById(R.id.txtCantidadSeleccionada)
        val btnIncrementar: Button = itemView.findViewById(R.id.btnIncrementar)
        val btnDecrementar: Button = itemView.findViewById(R.id.btnDecrementar)
        val btnAgregar: Button = itemView.findViewById(R.id.btnAgregar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]

        holder.imgProducto.setImageResource(producto.imagen)
        holder.txtNombre.text = producto.nombre
        holder.txtDescripcion.text = producto.descripcion
        holder.txtPrecio.text = "Precio: $${"%.2f".format(producto.precio)}"
        holder.txtCantidadDisponible.text = "Disponible: ${producto.cantidad} unds"
        holder.txtCantidadSeleccionada.text = "1"

        var cantidadSeleccionada = 1

        holder.btnIncrementar.setOnClickListener {
            if (cantidadSeleccionada < producto.cantidad) {
                cantidadSeleccionada++
                holder.txtCantidadSeleccionada.text = cantidadSeleccionada.toString()
            } else {
                Toast.makeText(holder.itemView.context,
                    "No hay suficiente stock", Toast.LENGTH_SHORT).show()
            }
        }

        holder.btnDecrementar.setOnClickListener {
            if (cantidadSeleccionada > 1) {
                cantidadSeleccionada--
                holder.txtCantidadSeleccionada.text = cantidadSeleccionada.toString()
            }
        }

        holder.btnAgregar.setOnClickListener {
            val productoConCantidad = producto.copy(cantidad = cantidadSeleccionada)
            Carrito.agregarProducto(productoConCantidad)
            Toast.makeText(
                holder.itemView.context,
                "Añadido: ${producto.nombre} x$cantidadSeleccionada",
                Toast.LENGTH_SHORT
            ).show()

            cantidadSeleccionada = 1
            holder.txtCantidadSeleccionada.text = "1"
        }
    }

    override fun getItemCount(): Int = productos.size
}