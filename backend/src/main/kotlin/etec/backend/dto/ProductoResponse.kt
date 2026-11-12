package etec.backend.dto

import etec.backend.entity.Producto
import tools.jackson.databind.annotation.JsonSerialize
import tools.jackson.databind.ser.std.ToStringSerializer
import java.math.BigDecimal

data class ProductoResponse(
    val id: Long? = null,
    val nombre: String,
    val descripcion: String?,
    val imagen: String,
    val imageAlt: String?,
    val categoria: String?,

    @JsonSerialize(using = ToStringSerializer::class)
    val precio: BigDecimal,

    val material: String?,
    val tamano: String?,
    val colores: String?,

    val stock: Int,
    val activo: Boolean
) {
    companion object {
        fun fromEntity(p: Producto): ProductoResponse =
            ProductoResponse(
                id = p.id ?: 0L,
                nombre = p.nombre,
                descripcion = p.descripcion,
                imagen = p.imagen,
                imageAlt = p.imageAlt,
                categoria = p.categoria,
                precio = p.precio,
                material = p.material,
                tamano = p.tamano,
                colores = p.colores,
                stock = p.stock,
                activo = p.activo
            )
    }
}