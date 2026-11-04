package etec.backend.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "productos")
class Producto(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, length = 120)
    var nombre: String,

    @Column(nullable = false, length = 300)
    var descripcion: String,

    @Column(nullable = false, length = 100)
    var categoria: String,

    @Column(name = "personalizado")
    var personalizado: Boolean? = null,

    @Column(nullable = false, precision = 10, scale = 2)
    var precio: BigDecimal,

    @Column(nullable = false)
    var stock: Int,

    @Column(nullable = false)
    var activo: Boolean
) {
    // Constructor vacío requerido por JPA (si no usas el plugin no-arg de Kotlin)
    constructor() : this(
        id = null,
        nombre = "",
        descripcion = "",
        categoria = "",
        personalizado = null,
        precio = BigDecimal.ZERO,
        stock = 0,
        activo = false
    )
}