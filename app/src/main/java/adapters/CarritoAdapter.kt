package adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo12.R
import models.Producto

class CarritoAdapter(
    private val carrito: MutableList<Producto>,
    private val onEliminarClick: (Producto) -> Unit
) : RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>() {

    class CarritoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgProducto: ImageView = view.findViewById(R.id.imgCarritoProducto)
        val txtNombre: TextView = view.findViewById(R.id.txtNombreCarrito)
        val txtCantidad: TextView = view.findViewById(R.id.txtCantidadCarrito)
        val txtPrecio: TextView = view.findViewById(R.id.txtPrecioCarrito)
        val txtSubtotal: TextView = view.findViewById(R.id.txtSubtotalCarrito)
        val btnEliminar: Button = view.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_carrito, parent, false)
        return CarritoViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val producto = carrito[position]
        holder.imgProducto.setImageResource(producto.imagen)
        holder.txtNombre.text = producto.nombre
        holder.txtCantidad.text = "Cantidad: ${producto.cantidad}"
        holder.txtPrecio.text = "Precio: $${producto.precio}"
        holder.txtSubtotal.text = "Subtotal: $${producto.precio * producto.cantidad}"

        holder.btnEliminar.setOnClickListener {
            onEliminarClick(producto)
            notifyItemRemoved(holder.adapterPosition)
            notifyItemRangeChanged(holder.adapterPosition, carrito.size)
        }
    }


    override fun getItemCount(): Int = carrito.size
}
