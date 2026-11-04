package etec.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "cliente")
class Cliente(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, length = 100)
    var nombre: String,

    @Column(length = 30)
    var telefono: String? = null,

    @Column(length = 150)
    var email: String? = null,

    @Column(length = 200)
    var direccion: String? = null
) {
    // Constructor vacío requerido por JPA
    constructor() : this(
        id = null,
        nombre = "",
        telefono = null,
        email = null,
        direccion = null
    )
}