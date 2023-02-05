package zubkov.vadim.pruebasandroiddiseo.Screens.Homes

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import zubkov.vadim.pruebasandroiddiseo.Dto.StaticData
import zubkov.vadim.pruebasandroiddiseo.Model.Ruta
import zubkov.vadim.pruebasandroiddiseo.Screens.Homes.Models.*
import zubkov.vadim.pruebasandroiddiseo.Screens.Models.BottomBarContent
import zubkov.vadim.pruebasandroiddiseo.Screens.Models.TopBarContent


@Composable
fun HomeScreenOne(navigationController: NavHostController) {
    var showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        DialogoNuevaRuta(true) { showDialog.value = false }
    }

    var filter = FilterViewModel()
    filter.updateListado(StaticData().getRutas())
    filter.updateFiltroActividades(StaticData().categorias.toMutableList())
    Scaffold(
        topBar = { TopBarContent() },
        bottomBar = { BottomBarContent(navigationController) }
    ) {
        Scaffold (
            floatingActionButton = {
                FloatingActionButton(onClick = {showDialog.value = true}, backgroundColor = MaterialTheme.colors.secondary, modifier = Modifier.padding(0.dp,50.dp), contentColor = Color.White)
                {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
                }
            }
            ){
            EstructuraVentanaHome(filter)
        }
    }
}

@Composable
private fun EstructuraVentanaHome(filter:FilterViewModel) {

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .border(border = BorderStroke(0.3.dp, Color.Black)))
    {
        Spacer(modifier = Modifier.height(10.dp))
        Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
            Titulo("Panel de Rutas")
        }
        Separador()
        Buscador(filter)
        Filtros(filter)
        Separador()
        ListadoRutas(filter)
    }
}


@Composable
fun ListadoRutas(filter:FilterViewModel)
{
    val listaRutas: List<Ruta> by filter.listado.observeAsState(initial = StaticData().getRutas())

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {
        for (i in listaRutas) {
            Tarjeta(i)
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp))
    }
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
fun Separador(){
    Divider(color = MaterialTheme.colors.secondary, modifier = Modifier
        .fillMaxWidth()
        .padding(0.dp, 10.dp, 0.dp, 0.dp)
        .height(1.dp))
}

@Composable
fun Boton(texto:String,icono:ImageVector = Icons.Default.Add){

    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = MaterialTheme.colors.secondary,
        ),
        modifier = Modifier
            .height(85.dp)
            .fillMaxWidth()
            .padding(15.dp, 10.dp),
        shape = RoundedCornerShape(70),
        border = BorderStroke(2.dp, MaterialTheme.colors.secondary)
    ) {
        Icon(imageVector = icono, contentDescription = texto)
        Text(text = texto)
    }
}