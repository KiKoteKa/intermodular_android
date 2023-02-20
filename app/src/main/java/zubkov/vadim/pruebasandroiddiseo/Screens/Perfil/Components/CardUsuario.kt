package zubkov.vadim.pruebasandroiddiseo.Screens.Perfil.Components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import zubkov.vadim.pruebasandroiddiseo.Model.Ruta
import zubkov.vadim.pruebasandroiddiseo.Model.Usuario
import zubkov.vadim.pruebasandroiddiseo.Navigation.Routes

@Composable
@ExperimentalFoundationApi
fun CardUsuario(usuario:Usuario, navigationController:NavHostController) {
    Card(
        modifier = Modifier
            .padding(0.dp,0.dp,0.dp,1.dp)
            .fillMaxWidth()
            .clickable {
                navigationController.navigate("user/${usuario.id}/true")
            },
                elevation = 0.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(9.dp)

        ) {
            Image(
                painter = painterResource(usuario.imagen),
                contentDescription = "Foto Perfil Usuario",
                modifier = Modifier
                    .padding(10.dp,0.dp,0.dp,0.dp)
                    .size(48.dp)
                    .clip(RoundedCornerShape(14.dp))
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = usuario.nick,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = usuario.nombre + " "+ usuario.apellidos,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}

@Composable
fun CardRuta(ruta: Ruta,navigationController: NavHostController){
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable{navigationController.navigate("card/${ruta.id}")},
        painter = painterResource(ruta.imagenes.get(0)),
        alignment = Alignment.CenterStart,
        contentDescription = "",
        contentScale = ContentScale.Crop
    )
}