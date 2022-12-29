package zubkov.vadim.pruebasandroiddiseo.Screens.Homes

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import zubkov.vadim.pruebasandroiddiseo.Screens.Logins.Inicio
import zubkov.vadim.pruebasandroiddiseo.Screens.Models.BottomBarContent
import zubkov.vadim.pruebasandroiddiseo.Screens.Models.TopBarContent

@Composable
fun HomeScreenOne(navigationController: NavHostController) {
    Scaffold(
        topBar = { TopBarContent() },
        bottomBar = { BottomBarContent(navigationController) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("Home")
        }
    }
}