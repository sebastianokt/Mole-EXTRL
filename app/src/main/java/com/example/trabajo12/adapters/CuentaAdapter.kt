import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo12.R
import com.example.trabajo12.models.Cuenta

class CuentaAdapter(
    private val cuentas: List<Cuenta>,
    private val onEditarClick: (Cuenta) -> Unit,
    private val onEliminarClick: (Cuenta) -> Unit
) : RecyclerView.Adapter<CuentaAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre = itemView.findViewById<TextView>(R.id.txtNombreCuenta)
        val correo = itemView.findViewById<TextView>(R.id.txtCorreoCuenta)
        val btnEditar = itemView.findViewById<Button>(R.id.btnEditarCuenta)
        val btnEliminar = itemView.findViewById<Button>(R.id.btnEliminarCuenta)

        fun bind(cuenta: Cuenta) {
            nombre.text = cuenta.nombre
            correo.text = cuenta.correo

            btnEditar.setOnClickListener { onEditarClick(cuenta) }
            btnEliminar.setOnClickListener { onEliminarClick(cuenta) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cuenta, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cuentas[position])
    }

    override fun getItemCount(): Int = cuentas.size
}
