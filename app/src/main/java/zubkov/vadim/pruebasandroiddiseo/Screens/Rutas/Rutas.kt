package zubkov.vadim.pruebasandroiddiseo.Screens.Rutas

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import zubkov.vadim.pruebasandroiddiseo.Components.Tarjeta
import zubkov.vadim.pruebasandroiddiseo.Dto.StaticData
import zubkov.vadim.pruebasandroiddiseo.Model.Ruta
import zubkov.vadim.pruebasandroiddiseo.Navigation.Routes
import zubkov.vadim.pruebasandroiddiseo.Screens.Rutas.Models.*
import zubkov.vadim.pruebasandroiddiseo.Screens.Scaffold.TopBarContent
import zubkov.vadim.pruebasandroiddiseo.Screens.Scaffold.CustomBottomBar


@Composable
fun HomeScreenOne(navigationController: NavHostController) {
    var filter = FilterViewModel()
    filter.updateListado(StaticData().getRutas())
    filter.updateFiltroActividades(StaticData().categorias.toMutableList())
    Scaffold(
        topBar = { TopBarContent() },
        bottomBar = {CustomBottomBar(navigationController)},
        floatingActionButtonPosition = FabPosition.Center,
            isFloatingActionButtonDocked = true,
            floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = {
                    navigationController.navigate(Routes.NewRuta.route)
                },
                contentColor = Color.White
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add ruta")
            }
        }
    ) {
        EstructuraVentanaHome(filter,navigationController)
    }
}

@Composable
private fun EstructuraVentanaHome(filter:FilterViewModel,navigationController: NavHostController) {

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .border(border = BorderStroke(0.3.dp, Color.Black)))
    {
        Buscador(filter)
        Filtros(filter)
        Separador()
        ListadoRutas(filter,navigationController)
    }
}

@Composable
fun ListadoRutas(filter:FilterViewModel,navigationController: NavHostController)
{
    val listaRutas: List<Ruta> by filter.listado.observeAsState(initial = StaticData().getRutas())

    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {
        listaRutas.forEach {
            Tarjeta(
                navigationController,
                it,
                onItemClicked = { card ->
                    navigationController.navigate("card/${card.id}")
                })
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp))
    }
}


