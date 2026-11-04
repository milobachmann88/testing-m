package etec.backend.service

import etec.backend.dto.DetallePedidoRequest
import etec.backend.dto.DetallePedidoResponse
import etec.backend.entity.DetallePedido
import etec.backend.exception.DetallePedidoNoEncontradoException
import etec.backend.exception.PedidoNoEncontradoException
import etec.backend.exception.ProductoNoEncontradoException
import etec.backend.repository.DetallePedidoRepository
import etec.backend.repository.PedidoRepository
import etec.backend.repository.ProductoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DetallePedidoService(
    private val detallePedidoRepository: DetallePedidoRepository,
    private val pedidoRepository: PedidoRepository,
    private val productoRepository: ProductoRepository
) {

    @Transactional(readOnly = true)
    fun listar(pageable: Pageable): Page<DetallePedidoResponse> {
        return detallePedidoRepository.findAll(pageable)
            .map { DetallePedidoResponse.fromEntity(it) }
    }

    @Transactional(readOnly = true)
    fun obtener(id: Long): DetallePedidoResponse {
        val detalle = detallePedidoRepository.findById(id)
            .orElseThrow { DetallePedidoNoEncontradoException(id) }
        return DetallePedidoResponse.fromEntity(detalle)
    }

    @Transactional
    fun crear(req: DetallePedidoRequest): DetallePedidoResponse {
        // Validar que el pedido y el producto existan antes de asociarlos
        val pedido = pedidoRepository.findById(req.pedidoId)
            .orElseThrow { PedidoNoEncontradoException(req.pedidoId) }

        val producto = productoRepository.findById(req.productoId)
            .orElseThrow { ProductoNoEncontradoException(req.productoId) }

        val nuevoDetalle = DetallePedido(
            cantidad = req.cantidad,
            precioUnitario = req.precioUnitario,
            pedido = pedido,
            producto = producto
        )
        val guardado = detallePedidoRepository.save(nuevoDetalle)
        return DetallePedidoResponse.fromEntity(guardado)
    }

    @Transactional
    fun actualizar(id: Long, req: DetallePedidoRequest): DetallePedidoResponse {
        val detalleExistente = detallePedidoRepository.findById(id)
            .orElseThrow { DetallePedidoNoEncontradoException(id) }

        val pedido = pedidoRepository.findById(req.pedidoId)
            .orElseThrow { PedidoNoEncontradoException(req.pedidoId) }

        val producto = productoRepository.findById(req.productoId)
            .orElseThrow { ProductoNoEncontradoException(req.productoId) }

        detalleExistente.cantidad = req.cantidad
        detalleExistente.precioUnitario = req.precioUnitario
        detalleExistente.pedido = pedido
        detalleExistente.producto = producto

        val actualizado = detallePedidoRepository.save(detalleExistente)
        return DetallePedidoResponse.fromEntity(actualizado)
    }

    @Transactional
    fun eliminar(id: Long) {
        val detalle = detallePedidoRepository.findById(id)
            .orElseThrow { DetallePedidoNoEncontradoException(id) }
        detallePedidoRepository.delete(detalle)
    }
}