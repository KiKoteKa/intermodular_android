package zubkov.vadim.pruebasandroiddiseo.Model

import zubkov.vadim.pruebasandroiddiseo.R
import java.util.*

data class Usuario (
    val id: String,
    val nombre: String,
    val apellidos: String,
    val email: String,
    val nick: String,
    val fechaNacimiento: Date,
    val imagen:Int = R.drawable.fotoperfil
)
