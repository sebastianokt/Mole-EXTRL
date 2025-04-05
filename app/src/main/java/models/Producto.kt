package models

data class Producto(
    val id: Int,
    val imagen: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    var cantidad: Int = 1,
    val categoria: String,
    )


