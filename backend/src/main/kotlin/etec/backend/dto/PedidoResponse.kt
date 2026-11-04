package etec.backend.dto

import etec.backend.entity.Pedido
import java.time.LocalDateTime

data class PedidoResponse(
    val id: Long? = null,
    val fecha: LocalDateTime?,
    val fechaEntrega: LocalDateTime?,
    val estado: String?,
    val clienteId: Int,
    val clienteNombre: String?
) {
    companion object {
        fun fromEntity(p: Pedido): PedidoResponse =
            PedidoResponse(
                id = p.id ?: 0L,
                fecha = p.fecha,
                fechaEntrega = p.fechaEntrega,
                estado = p.estado,
                clienteId = p.cliente?.id ?: 0,
                clienteNombre = p.cliente?.nombre
            )
    }
}