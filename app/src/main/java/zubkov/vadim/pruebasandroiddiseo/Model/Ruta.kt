package zubkov.vadim.pruebasandroiddiseo.Model

data class Ruta (
        val nombre:String,
        val imagen: Int,
        val km: Double,
        val lat: Double,
        val dificultad: Int,
        val tipoDeRuta: Int,
        val usuarioPublicado: String,
        val rating: Double,
        val icon: Int
)