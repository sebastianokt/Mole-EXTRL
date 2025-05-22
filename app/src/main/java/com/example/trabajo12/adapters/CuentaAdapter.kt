import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajo12.R
import com.example.trabajo12.models.Cuenta

class CuentaAdapter(private val cuentas: List<Cuenta>) :
    RecyclerView.Adapter<CuentaAdapter.CuentaViewHolder>() {

    class CuentaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre = view.findViewById<TextView>(R.id.txtNombreCuenta)
        val correo = view.findViewById<TextView>(R.id.txtCorreoCuenta)
        val cedula = view.findViewById<TextView>(R.id.txtCedulaCuenta)
        val celular = view.findViewById<TextView>(R.id.txtCelularCuenta)
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
    }

    override fun getItemCount(): Int = cuentas.size
}
