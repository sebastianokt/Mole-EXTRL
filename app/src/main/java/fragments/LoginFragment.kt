package fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.trabajo12.R

class LoginFragment : Fragment() {

    private lateinit var etCorreo: EditText
    private lateinit var etContrasena: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvIrARegistro: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        etCorreo = view.findViewById(R.id.etCorreo)
        etContrasena = view.findViewById(R.id.etContrasena)
        btnLogin = view.findViewById(R.id.btnLogin)
        tvIrARegistro = view.findViewById(R.id.tvIrARegistro)

        val sharedPref = requireActivity().getSharedPreferences("UsuarioPrefs", Context.MODE_PRIVATE)

        btnLogin.setOnClickListener {
            val correo = etCorreo.text.toString()
            val contrasena = etContrasena.text.toString()

            val correoGuardado = sharedPref.getString("correo", null)
            val contrasenaGuardada = sharedPref.getString("contrasena", null)

            if (correo == correoGuardado && contrasena == contrasenaGuardada) {
                Toast.makeText(requireContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                findNavController().navigate(
                    R.id.action_loginFragment_to_inicioFragment,
                    null,
                    NavOptions.Builder()
                        .setPopUpTo(R.id.loginFragment, true)
                        .build()
                )            } else {
                Toast.makeText(requireContext(), "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        tvIrARegistro.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registroFragment)
        }

        return view
    }
}
