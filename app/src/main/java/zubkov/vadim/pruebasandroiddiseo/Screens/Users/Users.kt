package zubkov.vadim.pruebasandroiddiseo.Screens.Users

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceAround
import androidx.compose.foundation.layout.Arrangement.SpaceAround
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import zubkov.vadim.pruebasandroiddiseo.Model.Usuario
import zubkov.vadim.pruebasandroiddiseo.Screens.Models.BottomBarContent
import zubkov.vadim.pruebasandroiddiseo.Screens.Models.TopBarContent
import java.text.SimpleDateFormat

@Composable
fun UsersScreen(navigationController: NavHostController) {
    val user = Usuario("123","Pepito","Alonso","ejemplo@gmail.com","Pipa","14/02/2000")
    Scaffold(
        topBar = { TopBarContent() },
        bottomBar = { BottomBarContent(navigationController) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EstructuraVentanaUser(user)
        }
    }
}

@Composable
private fun EstructuraVentanaUser(usuario:Usuario) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().border(border = BorderStroke(0.3.dp, Color.Black)))
    {
        Spacer(modifier = Modifier.height(10.dp))
        Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
            Spacer(modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp / 2 - 61.dp))
                Titulo("Panel de Cuenta")
                Row(horizontalArrangement = Arrangement.End, verticalAlignment = Alignment.CenterVertically,modifier = Modifier.fillMaxWidth())
                {
                    BotonLogOut()
                }
            }
            Separador()
            ImagenRedonda(usuario.imagen)
            TextoPrincipal(usuario.nick)
            Separador()
            Texto(usuario.nombre, " Nombre")
            Texto(usuario.apellidos, " Apellidos", Icons.Default.AccountBox)
            Texto(usuario.email, " Email", Icons.Default.Email)
            Texto(usuario.fechaNacimiento, " F. Nacimiento", Icons.Default.DateRange)
            Separador()
            Boton(" Editar perfil", Icons.Default.Edit)
    }
}

@Composable
fun Boton(texto:String,icono:ImageVector){

    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = MaterialTheme.colors.secondary,
        ),
        modifier = Modifier
            .size(330.dp, 65.dp)
            .width(330.dp),
        shape = RoundedCornerShape(70),
        border = BorderStroke(2.dp, MaterialTheme.colors.secondary)
    ) {
        Icon(imageVector = icono, contentDescription = texto)
        Text(text = texto)
    }
}

@Composable
fun Texto(valor:String,texto:String,icono:ImageVector = Icons.Default.Person){
    Column(modifier = Modifier.fillMaxWidth().padding(30.dp,2.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp / 3), horizontalArrangement = Arrangement.Start) {
                Icon(imageVector = icono, contentDescription = texto, tint = MaterialTheme.colors.secondary)
                Text(
                    text = texto,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp*2/ 3), horizontalArrangement = Arrangement.End) {
                Text(
                    color = Color.Gray,
                    text = valor,
                    fontSize = 16.sp
                )
            }
        }
        Divider(color = Color.LightGray, modifier = Modifier.fillMaxWidth().padding(5.dp,10.dp).height(1.3.dp))
    }
}

@Composable
fun TextoPrincipal(texto:String){
    Text(text = texto,
        modifier = Modifier.padding(0.dp,20.dp,0.dp,10.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun Titulo(texto:String){
    Text(text = texto,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color=MaterialTheme.colors.secondary
    )
}

@Composable
fun BotonLogOut(){
    IconButton(onClick = { })
    {
        Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = "LogOut", tint = MaterialTheme.colors.secondary)
    }
}

@Composable
fun Separador(){
    Divider(color = MaterialTheme.colors.secondary, modifier = Modifier.fillMaxWidth().padding(0.dp,10.dp,0.dp,20.dp).height(1.dp))
}


@Composable
fun ImagenRedonda(idFoto:Int) {
    Image(
        painter = painterResource(idFoto),
        contentDescription = "Imagen",
        modifier = Modifier
            .size(150.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
    )
}
