package zubkov.vadim.pruebasandroiddiseo.Screens.Homes.Models

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zubkov.vadim.pruebasandroiddiseo.R
import zubkov.vadim.pruebasandroiddiseo.Screens.Homes.ContentRutas

@Composable
fun MainCard(contendioRutas: ContentRutas){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        backgroundColor = Color(0xFF3F826D)
    ){
        Column() {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(8.dp)
            ){
                TipoRuta(tipoRuta = contendioRutas)
                Spacer(modifier = Modifier.padding(6.dp))
                TituloRuta(tituloRuta = contendioRutas)
            }
            divisor(
                modifier= Modifier
                    .background(Color(0xFF0D78AA))
                    .height(1.dp)
                    .fillMaxWidth()
            )
            Spacer(Modifier.padding(5.dp))
            Row(){
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp, bottom = 5.dp)
                ) {
                    Spacer(modifier = Modifier.padding(5.dp))
                    Distancia(distancia = contendioRutas)
                    Spacer(Modifier.padding(10.dp))
                    Desnivel(desnivel = contendioRutas)
                    Spacer(Modifier.padding(19.dp))
                    Likes(likes = contendioRutas)
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier
                        .padding(end = 10.dp, bottom = 5.dp)
                ){
                    Spacer(modifier = Modifier.padding(5.dp))
                    Imagen(imagen = contendioRutas)
                    Spacer(modifier = Modifier.padding(6.dp))
                    Usuario(usuario = contendioRutas,icon = contendioRutas)
                }
            }
        }
    }
}
@Composable
fun divisor(modifier: Modifier){
    Divider(
        modifier = modifier
    )
}

@Composable
fun TipoRuta(tipoRuta: ContentRutas){
    var imagenes = when (tipoRuta.tipoDeRuta){
        1 -> R.drawable.person
        else -> R.drawable.person
    }
    Box(
        modifier = Modifier
            .size(40.dp)
    ){
        Image(
            painterResource(id = imagenes),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
    }
}

@Composable
fun TituloRuta(tituloRuta: ContentRutas){
    Text(
        text = tituloRuta.nombre,
        fontSize = 17.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium
    )
}

@Composable
fun Distancia(distancia: ContentRutas){
    Box(){
        Column(){
            Text(
                text = "Distancia",
                fontSize = 17.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "${distancia.km} Km",
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Medium
            )
        }
    }

}

@Composable
fun Desnivel(desnivel: ContentRutas){
    Box(){
        Column() {
            Text(
                text = "Desnivel",
                fontSize = 17.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = "${desnivel.lat} m",
                fontSize = 14.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Medium
            )
        }
    }
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
fun Usuario(usuario: ContentRutas,icon: ContentRutas){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(end = 20.dp)
    ){
        var imagenes = when (icon.icon){
            1 -> R.drawable.person
            else -> R.drawable.person
        }
        Box(
            modifier = Modifier
                .size(30.dp)
                .border(BorderStroke(1.dp, Color.Black))
        ){
            Image(
                painterResource(id = imagenes),
                contentDescription = "",
                contentScale = ContentScale.FillBounds
            )
        }
        Spacer(Modifier.padding(5.dp))
        Text(
            text = usuario.usuarioPublicado
        )
    }

}

@Composable
fun Likes(likes: ContentRutas){
    Row(
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "",
            modifier = Modifier
                .size(20.dp)
        )
        Spacer(Modifier.padding(5.dp))
        Text(
            text = "${likes.rating}"
        )
    }

}