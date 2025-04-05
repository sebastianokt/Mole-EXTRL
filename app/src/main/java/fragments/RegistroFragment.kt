package fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trabajo12.R

class RegistroFragment : Fragment() {

    private lateinit var etNombre: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etTelefono: EditText
    private lateinit var etContrasena: EditText
    private lateinit var btnRegistrar: Button
    private lateinit var tvIrLogin: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_registro, container, false)

        etNombre = view.findViewById(R.id.etNombre)
        etCorreo = view.findViewById(R.id.etCorreo)
        etTelefono = view.findViewById(R.id.etTelefono)
        etContrasena = view.findViewById(R.id.etContrasena)
        btnRegistrar = view.findViewById(R.id.btnRegistrar)
        tvIrLogin = view.findViewById(R.id.tvIrLogin)

        val sharedPref = requireActivity().getSharedPreferences("UsuarioPrefs", Context.MODE_PRIVATE)

        btnRegistrar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val correo = etCorreo.text.toString()
            val telefono = etTelefono.text.toString()
            val contrasena = etContrasena.text.toString()

            if (nombre.isNotBlank() && correo.isNotBlank() && telefono.isNotBlank() && contrasena.isNotBlank()) {
                sharedPref.edit().apply {
                    putString("nombre", nombre)
                    putString("correo", correo)
                    putString("telefono", telefono)
                    putString("contrasena", contrasena)
                    apply()
                }

                Toast.makeText(requireContext(), "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_registroFragment_to_loginFragment)
            } else {
                Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        tvIrLogin.setOnClickListener {
            findNavController().navigate(R.id.action_registroFragment_to_loginFragment)
        }

        return view
    }
}
