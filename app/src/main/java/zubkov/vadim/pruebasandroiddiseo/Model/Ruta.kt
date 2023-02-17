package zubkov.vadim.pruebasandroiddiseo.Model

import java.util.*

data class Ruta (
        val id : Int,
        val fecha : Date,
        val nombre : String,
        val categoria: Int,
        val distancia: Double,
        val dificultad: Int,
        val duracion : Int,
        val descripcion : String,
        val usuarioPublicado: Usuario,
        val imagenes: List<Int>,
        val valoracion : Double
)