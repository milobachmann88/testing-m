package etec.backend.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "detallePedido")
class DetallePedido(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(nullable = false)
    var cantidad: Int = 1,

    // Se recomienda BigDecimal para precios en lugar de Float para evitar problemas de precisión
    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    var precioUnitario: BigDecimal,

    // Relación con la entidad Pedido
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    var pedido: Pedido,

    // Relación con la entidad Producto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    var producto: Producto
) {
    // Constructor vacío requerido por JPA
    constructor() : this(
        id = null,
        cantidad = 1,
        precioUnitario = BigDecimal.ZERO,
        pedido = Pedido(),
        producto = Producto()
    )
}