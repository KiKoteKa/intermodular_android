package zubkov.vadim.pruebasandroiddiseo.Screens.Homes.Models

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import zubkov.vadim.pruebasandroiddiseo.Dto.StaticData
import zubkov.vadim.pruebasandroiddiseo.Model.Ruta
import zubkov.vadim.pruebasandroiddiseo.R
import java.text.SimpleDateFormat

@Composable
fun Tarjeta(ruta: Ruta) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = Color.LightGray,
        modifier = Modifier
            .height(240.dp)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp, 0.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(2f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = ruta.nombre,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                )

                Spacer(modifier = Modifier.height(15.dp))


                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = RoundedCornerShape(24.dp),
                            modifier = Modifier.wrapContentSize().padding(4.dp),
                            color = Color(0xFFD1D5E1)
                        ) {
                            Text(
                                text = StaticData().getDificultad(ruta.dificultad),
                                fontSize = 10.sp,
                                modifier = Modifier.padding(vertical = 4.dp, horizontal = 6.dp)
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(24.dp),
                            modifier = Modifier.wrapContentSize().padding(4.dp),
                            color = Color(0xFFD1D5E1)
                        ) {
                            Text(
                                text = ruta.distancia.toString() + " km (" + ruta.duracion.toString() + " mins)",
                                fontSize = 10.sp,
                                modifier = Modifier.padding(vertical = 4.dp, horizontal = 6.dp)
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(24.dp),
                            modifier = Modifier.wrapContentSize().padding(4.dp),
                            color = Color(0xFFD1D5E1)
                        ) {
                            Text(
                                text = StaticData().getCategoria(ruta.categoria),
                                fontSize = 10.sp,
                                modifier = Modifier.padding(vertical = 4.dp, horizontal = 6.dp)
                            )
                        }
                    }

                Spacer(modifier = Modifier.height(15.dp))

                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(15.dp,0.dp,0.dp,10.dp).fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = ruta.valoracion.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        Icons.Default.Star,
                        tint = Color(0xFFF6B266),
                        contentDescription = null
                    )

                    Icon(
                        Icons.Default.Star,
                        tint = Color(0xFFF6B266),
                        contentDescription = null
                    )

                    Icon(
                        Icons.Default.Star,
                        tint = Color(0xFFF6B266),
                        contentDescription = null
                    )

                    Icon(
                        Icons.Default.Star,
                        tint = Color(0xFFF6B266),
                        contentDescription = null
                    )
                    Icon(
                        Icons.Default.Star,
                        tint = Color(0xFFF6B266),
                        contentDescription = null
                    )
                }


                Row(modifier = Modifier.padding(50.dp,0.dp,0.dp,0.dp).fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically) {
                    OutlinedButton(
                        modifier = Modifier.size(140.dp,46.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White,
                            backgroundColor = MaterialTheme.colors.secondary
                        ),
                        onClick = { /*TODO*/ }
                    ) {
                        Text(
                            text = "Saber Más",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Spacer(modifier = Modifier.width(80.dp))
                    Surface(
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier.wrapContentSize(),
                        color = Color(0xFFD1D5E1)
                    ) {

                    }
                }
            }




            Surface(
                modifier = Modifier
                    .size(width = 120.dp, height = 240.dp)
                    .padding(0.dp, 10.dp, 0.dp, 0.dp),
                color = Color.LightGray
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row() {
                        Image(
                            painter = painterResource(id = R.drawable.background),
                            contentScale = ContentScale.Crop,
                            contentDescription = null,
                            modifier = Modifier.size(width = 120.dp, height = 120.dp).clip(RoundedCornerShape(16.dp))
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ImagenUsuario(ruta.usuarioPublicado.imagen)
                        Text(
                            text = ruta.usuarioPublicado.nick,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(vertical = 4.dp, horizontal = 15.dp)
                        )
                    }
                    Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = SimpleDateFormat("dd/MM/yyyy").format(ruta.fecha),
                            color = Color.White,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun ImagenUsuario(idFoto:Int) {
    Image(
        painter = painterResource(idFoto),
        contentDescription = "Imagen",
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
    )
}