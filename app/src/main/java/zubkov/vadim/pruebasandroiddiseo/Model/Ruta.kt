package zubkov.vadim.pruebasandroiddiseo.Model

import java.util.*

data class Ruta (
        val id : String,
        val fecha : Date,
        val nombre : String,
        val categoria: Int,
        val distancia: Double,
        val dificultad: Int,
        val duracion : Int,
        val descripcion : String,
        val usuarioPublicado: Usuario,
        val imagen: Int,
        val valoracion : Double
)