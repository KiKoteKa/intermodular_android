package zubkov.vadim.pruebasandroiddiseo.Screens.Homes

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import zubkov.vadim.pruebasandroiddiseo.Screens.Homes.Models.MainCard
import zubkov.vadim.pruebasandroiddiseo.Screens.Logins.Inicio
import zubkov.vadim.pruebasandroiddiseo.Screens.Models.BottomBarContent
import zubkov.vadim.pruebasandroiddiseo.Screens.Models.TopBarContent

data class ContentRutas(
    val nombre:String,
    val imagen: Int,
    val km: Double,
    val lat: Double,
    val dificultad: String,
    val tipoDeRuta: Int,
    val usuarioPublicado: String,
    val rating: Double,
    val icon: Int
)

private val rutas : MutableList<ContentRutas> = mutableListOf(
    ContentRutas("Ruta de Ordesa",1,13.2,550.0,"dificil",1,"Paco",3.6,1),
    ContentRutas("Ruta de Condesa",2,10.2,850.0,"mediana",1,"Maria",4.6,1)
)

@Composable
fun HomeScreenOne(navigationController: NavHostController) {
    Scaffold(
        topBar = { TopBarContent() },
        bottomBar = { BottomBarContent(navigationController) }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE1E6E1))
        ) {
            items(rutas) { rutas ->
                MainCard(contendioRutas = rutas)
            }
        }
    }
}