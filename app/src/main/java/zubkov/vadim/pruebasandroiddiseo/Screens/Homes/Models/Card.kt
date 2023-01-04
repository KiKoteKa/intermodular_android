package zubkov.vadim.pruebasandroiddiseo.Screens.Homes.Models

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import zubkov.vadim.pruebasandroiddiseo.R
import zubkov.vadim.pruebasandroiddiseo.Screens.Homes.ContentRutas

@Composable
fun MainCard(contendioRutas: ContentRutas){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        backgroundColor = Color.Red
    ){
        Column() {
            Row(){
                TipoRuta(tipoRuta = contendioRutas)
                Spacer(modifier = Modifier.padding(5.dp))
                TituloRuta(tituloRuta = contendioRutas)
            }
            Spacer(Modifier.padding(5.dp))
            Row(){
                Column() {
                    Distancia(distancia = contendioRutas)
                    Spacer(Modifier.padding(5.dp))
                    Desnivel(desnivel = contendioRutas)
                }
                Spacer(modifier = Modifier.weight(1f))
                Imagen(imagen = contendioRutas)
            }
            Spacer(Modifier.padding(5.dp))
            Row(){
                Likes(likes = contendioRutas)
                Spacer(modifier = Modifier.weight(1f))
                Usuario(usuario = contendioRutas)
            }
        }
    }
}

@Composable
fun TipoRuta(tipoRuta: ContentRutas){
    Text(
        text = tipoRuta.tipoDeRuta
    )
}

@Composable
fun TituloRuta(tituloRuta: ContentRutas){
    Text(
        text = tituloRuta.nombre
    )
}

@Composable
fun Distancia(distancia: ContentRutas){
    Text(
        text = "${distancia.km}"
    )
}

@Composable
fun Desnivel(desnivel: ContentRutas){
    Text(
        text = "${desnivel.lat}"
    )
}

@Composable
fun Imagen(imagen: ContentRutas){
    var imagenes = when (imagen.imagen){
        1 -> R.drawable.foto
        2 -> R.drawable.background
        else -> R.drawable.foto
    }
    Box(
        modifier = Modifier
            .size(150.dp)
    ){
        Image(
            painterResource(id = imagenes),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun Usuario(usuario: ContentRutas){
    Text(
        text = usuario.usuarioPublicado
    )
}

@Composable
fun Likes(likes: ContentRutas){
    Text(
        text = "${likes.rating}"
    )
}