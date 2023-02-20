package zubkov.vadim.pruebasandroiddiseo.Components

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import zubkov.vadim.pruebasandroiddiseo.Model.Ruta
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import zubkov.vadim.pruebasandroiddiseo.Dto.StaticData
import zubkov.vadim.pruebasandroiddiseo.GlobalViewModel
import zubkov.vadim.pruebasandroiddiseo.Screens.Rutas.Models.BorrarRuta
import zubkov.vadim.pruebasandroiddiseo.Screens.Rutas.Models.CorazonFavorito

@Composable
fun Tarjeta(navigationController: NavHostController,globalViewModel: GlobalViewModel, ruta: Ruta, onItemClicked: (ruta: Ruta) -> Unit) {
    val tarjetaDeUsuario = globalViewModel.usuarioRegistrado.value!!.id == ruta.usuarioPublicado.id
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable(onClick = { onItemClicked(ruta) }),
        elevation = 8.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = ruta.nombre,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp)
                )
            }
            IconButton(
                onClick = { /* Acción al hacer clic en el botón */ },
                modifier = Modifier.size(48.dp)
            ) {
                if(!tarjetaDeUsuario) {
                    CorazonFavorito()
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp, 40.dp,16.dp,16.dp).fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .padding(0.dp, 30.dp, 0.dp, 0.dp)
                    .weight(1f)
                    .width(10.dp)
            ) {
                Text(
                    text = "Distancia",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Text(
                    text = "${ruta.distancia} km",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 15.dp)
                )
                Text(
                    text = "Categoría",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Text(
                    text = StaticData().getCategoria(ruta.categoria),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 15.dp)
                )
                Text(
                    text = "Dificultad",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
                Text(
                    text = StaticData().getDificultad(ruta.dificultad),
                    fontSize = 16.sp,
                    modifier = Modifier.padding(bottom = 15.dp)
                )
            }

            Column(
                modifier = Modifier
                    .padding(10.dp, 0.dp, 0.dp, 10.dp)
                    .weight(2.25f)
                    .fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(0.dp,30.dp,0.dp,0.dp)) {
                    Image(
                        painter = painterResource(ruta.imagenes.get(0)),
                        contentDescription = "Primera Imagen de la Ruta",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(400.dp, 120.dp)
                            .padding(top = 0.dp)
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)) {
                    Image(
                        painter = painterResource(ruta.usuarioPublicado.imagen),
                        contentDescription = "Icono del Perfil del Usuario",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .clickable { navigationController.navigate("user/${ruta.usuarioPublicado.id}/true") }
                    )
                    Text(
                        text = " ${ruta.usuarioPublicado.nick}",
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(5.dp,0.dp,0.dp,0.dp)
                    )
                }
            }
        }
    }
}
