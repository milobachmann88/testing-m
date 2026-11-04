package etec.backend.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "pedidos")
class Pedido(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    // Permitimos que la base de datos maneje el DEFAULT CURRENT_TIMESTAMP
    @Column(name = "fecha", insertable = false, updatable = false)
    var fecha: LocalDateTime? = null,

    @Column(name = "fecha_entrega")
    var fechaEntrega: LocalDateTime? = null,

    @Column(length = 50)
    var estado: String? = null,

    // Mapeo de la clave foránea cliente_id con la entidad Cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    var cliente: Cliente
) {
    // Constructor vacío requerido por JPA
    constructor() : this(
        id = null,
        fecha = null,
        fechaEntrega = null,
        estado = null,
        cliente = Cliente()
    )
}