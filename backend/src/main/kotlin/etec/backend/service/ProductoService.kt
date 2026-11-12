package etec.backend.service

import etec.backend.dto.ProductoRequest
import etec.backend.dto.ProductoResponse
import etec.backend.entity.Producto
import etec.backend.exception.ProductoNoEncontradoException
import etec.backend.repository.ProductoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductoService(
    private val productoRepository: ProductoRepository
) {

    @Transactional(readOnly = true)
    fun listar(pageable: Pageable): Page<ProductoResponse> {
        return productoRepository.findAll(pageable)
            .map { ProductoResponse.fromEntity(it) }
    }

    @Transactional(readOnly = true)
    fun obtener(id: Long): ProductoResponse {
        val producto = productoRepository.findById(id)
            .orElseThrow { ProductoNoEncontradoException(id) }
        return ProductoResponse.fromEntity(producto)
    }

    @Transactional
    fun crear(req: ProductoRequest): ProductoResponse {
        val nuevoProducto = Producto(
            nombre = req.nombre,
            descripcion = req.descripcion.toString(),
            imagen = req.imagen,
            imageAlt = req.imageAlt,
            categoria = req.categoria.toString(),
            categoria = req.categoria.toString(),
            precio = req.precio,
            tamano = req.tamano,
            colores = req.colores,
            stock = req.stock,
            stock = req.stock,
            activo = req.activo ?: true
        )
        val guardado = productoRepository.save(nuevoProducto)
        return ProductoResponse.fromEntity(guardado)
    }

    @Transactional
    fun actualizar(id: Long, req: ProductoRequest): ProductoResponse {
        val productoExistente = productoRepository.findById(id)
            .orElseThrow { ProductoNoEncontradoException(id) }

        productoExistente.nombre = req.nombre
        productoExistente.descripcion = req.descripcion.toString()
        productoExistente.imagen = req.imagen
        productoExistente.imageAlt = req.imageAlt
        productoExistente.categoria = req.categoria.toString()
        productoExistente.precio = req.precio
        productoExistente.material = req.material
        productoExistente.tamano = req.tamano
        productoExistente.colores = req.colores
        productoExistente.stock = req.stock
        productoExistente.activo = req.activo ?: productoExistente.activo

        val actualizado = productoRepository.save(productoExistente)
        return ProductoResponse.fromEntity(actualizado)
    }

    @Transactional
    fun eliminar(id: Long) {
        val producto = productoRepository.findById(id)
            .orElseThrow { ProductoNoEncontradoException(id) }
        productoRepository.delete(producto)
    }
}