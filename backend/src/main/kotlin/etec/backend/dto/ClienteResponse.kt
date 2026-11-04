package etec.backend.dto

import etec.backend.entity.Cliente

data class ClienteResponse(
    val id: Long? = null,
    val nombre: String,
    val telefono: String?,
    val email: String?,
    val direccion: String?
) {
    companion object {
        fun fromEntity(c: Cliente): ClienteResponse =
            ClienteResponse(
                id = c.id ?: 0L,
                nombre = c.nombre,
                telefono = c.telefono,
                email = c.email,
                direccion = c.direccion
            )
    }
}