package zubkov.vadim.pruebasandroiddiseo.Screens.Homes

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    val tipoDeRuta: String,
    val usuarioPublicado: String,
    val rating: Double
)

private val rutas : MutableList<ContentRutas> = mutableListOf(
    ContentRutas("Ruta de Ordesa",1,13.2,550.0,"dificil","senderismo","Paco",3.6),
    ContentRutas("Ruta de Condesa",2,10.2,850.0,"mediana","ciclismo","Maria",4.6)
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
        ) {
            items(rutas) { rutas ->
                MainCard(contendioRutas = rutas)
            }
        }
    }
}