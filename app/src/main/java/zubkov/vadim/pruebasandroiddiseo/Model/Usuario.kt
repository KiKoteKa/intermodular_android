package zubkov.vadim.pruebasandroiddiseo.Model

import zubkov.vadim.pruebasandroiddiseo.R
import java.util.*

data class Usuario (
    val id: Int,
    val nombre: String,
    val apellidos: String,
    val descripcion: String,
    val email: String,
    val nick: String,
    val fechaNacimiento: Date,
    val rutas:List<Int>,
    val rutasSeguidas:List<Int>,
    val seguidos:List<Int>,
    val seguidores:List<Int>,
    val imagen:Int = R.drawable.fotoperfil,
)
