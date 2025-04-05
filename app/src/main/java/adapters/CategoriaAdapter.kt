package adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo12.R
import models.Categoria

class CategoriaAdapter(
    private val categorias: List<Categoria>,
    private val onItemClick: (Categoria) -> Unit
) : RecyclerView.Adapter<CategoriaAdapter.CategoriaViewHolder>() {

    class CategoriaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgCategoria: ImageView = view.findViewById(R.id.imgCategoria)
        val txtNombre: TextView = view.findViewById(R.id.txtNombreCategoria)
        val txtCantidad: TextView = view.findViewById(R.id.txtCantidadProductos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_categoria, parent, false)
        return CategoriaViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        val categoria = categorias[position]
        holder.imgCategoria.setImageResource(categoria.imagen)
        holder.txtNombre.text = categoria.nombre
        holder.txtCantidad.text = "${categoria.cantidadProductos} productos"

        holder.itemView.setOnClickListener {
            onItemClick(categoria)
        }
    }

    override fun getItemCount() = categorias.size
}
