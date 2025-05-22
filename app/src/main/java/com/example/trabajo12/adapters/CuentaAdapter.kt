import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo12.R
import com.example.trabajo12.models.Cuenta

class CuentaAdapter(
    private var cuentas: MutableList<Cuenta>,
    private val onDeleteClickListener: (Int) -> Unit
) : RecyclerView.Adapter<CuentaAdapter.CuentaViewHolder>() {

    inner class CuentaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.txtNombreCuenta)
        val correo: TextView = view.findViewById(R.id.txtCorreoCuenta)
        val cedula: TextView = view.findViewById(R.id.txtCedulaCuenta)
        val celular: TextView = view.findViewById(R.id.txtCelularCuenta)
        val btnEliminar: Button = view.findViewById(R.id.btnEliminarCuenta)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuentaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cuenta, parent, false)
        return CuentaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CuentaViewHolder, position: Int) {
        val cuenta = cuentas[position]
        holder.nombre.text = "Nombre: ${cuenta.nombre}"
        holder.correo.text = "Correo: ${cuenta.correo}"
        holder.cedula.text = "Cédula: ${cuenta.cedula}"
        holder.celular.text = "Celular: ${cuenta.celular}"

        holder.btnEliminar.setOnClickListener {
            onDeleteClickListener(position)
        }
    }

    override fun getItemCount(): Int = cuentas.size

    fun updateCuentas(newCuentas: MutableList<Cuenta>) {
        cuentas = newCuentas
        notifyDataSetChanged()
    }
}