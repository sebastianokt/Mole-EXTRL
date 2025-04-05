package models

object Carrito {
    private val productosEnCarrito = mutableListOf<Producto>()

    fun agregarProducto(producto: Producto) {
        val existente = productosEnCarrito.find { it.id == producto.id }
        if (existente != null) {
            existente.cantidad += producto.cantidad
        } else {
            productosEnCarrito.add(producto.copy())
        }
    }

    fun obtenerProductos(): List<Producto> {
        return productosEnCarrito
    }

    fun limpiarCarrito() {
        productosEnCarrito.clear()
    }

    fun eliminarProducto(producto: Producto) {
        productosEnCarrito.remove(producto)
    }
}
