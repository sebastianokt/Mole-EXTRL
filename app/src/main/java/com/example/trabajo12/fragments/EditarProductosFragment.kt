package com.example.trabajo12.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trabajo12.R
import com.example.trabajo12.models.Producto

class EditarProductosFragment : Fragment() {

    private lateinit var edtNombre: EditText
    private lateinit var edtPrecio: EditText
    private lateinit var edtDescripcion: EditText
    private lateinit var edtCantidad: EditText
    private lateinit var imgProducto: ImageView
    private lateinit var btnGuardarCambios: Button

    private var productoAEditar: Producto? = null
    private var indiceProducto: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_adm_editar_productos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edtNombre = view.findViewById(R.id.edtNombre)
        edtPrecio = view.findViewById(R.id.edtPrecio)
        edtDescripcion = view.findViewById(R.id.edtDescripcion)
        edtCantidad = view.findViewById(R.id.edtCantidad)
        imgProducto = view.findViewById(R.id.imgProducto)
        btnGuardarCambios = view.findViewById(R.id.btnGuardarCambios)

        arguments?.let {
            productoAEditar = it.getSerializable("producto") as? Producto
            indiceProducto = it.getInt("indice", -1)

            productoAEditar?.let { producto ->
                edtNombre.setText(producto.nombre)
                edtPrecio.setText(producto.precio.toString())
                edtDescripcion.setText(producto.descripcion)
                edtCantidad.setText(producto.cantidad.toString())
                imgProducto.setImageResource(producto.imagen)
            }
        }

        btnGuardarCambios.setOnClickListener {
            productoAEditar?.let { producto ->
                val actualizado = producto.copy(
                    nombre = edtNombre.text.toString(),
                    precio = edtPrecio.text.toString().toDoubleOrNull() ?: 0.0,
                    descripcion = edtDescripcion.text.toString(),
                    cantidad = edtCantidad.text.toString().toIntOrNull() ?: 1
                )

                guardarProductoActualizado(actualizado, indiceProducto)
                Toast.makeText(requireContext(), "Producto actualizado", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }
    }

    private fun guardarProductoActualizado(producto: Producto, indice: Int) {
        val prefs = requireContext().getSharedPreferences("productos", Context.MODE_PRIVATE)
        val editor = prefs.edit()


        val listaOriginal = prefs.getStringSet("productos_lista", mutableSetOf())?.toMutableList() ?: mutableListOf()

        if (indice in listaOriginal.indices) {

            val nuevoString = "${producto.id}|${producto.imagen}|${producto.nombre}|${producto.descripcion}|${producto.precio}|${producto.cantidad}|${producto.categoria}"
            listaOriginal[indice] = nuevoString
            editor.putStringSet("productos_lista", listaOriginal.toSet())
            editor.apply()
        }
    }
}
