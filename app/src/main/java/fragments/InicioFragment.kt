package fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.trabajo12.R

class InicioFragment : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inicio, container, false)

        activity?.title = "Inicio"

        val sharedPref = requireActivity().getSharedPreferences("UsuarioPrefs", Context.MODE_PRIVATE)
        val nombreUsuario = sharedPref.getString("nombre", "Cliente")
        view.findViewById<TextView>(R.id.tvBienvenida).text = "¡Bienvenido a OktShop, $nombreUsuario!"


        return view
    }
}