package zubkov.vadim.pruebasandroiddiseo.Screens.MapScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.maps.android.compose.GoogleMap
import zubkov.vadim.pruebasandroiddiseo.Screens.Models.BottomBarContent
import zubkov.vadim.pruebasandroiddiseo.Screens.Models.TopBarContent

@Composable
fun MapaGoogle(navigationController: NavHostController) {
    Scaffold(
        topBar = { TopBarContent()},
        bottomBar = { BottomBarContent(navigationController = navigationController)}
    ) {
        Box(
            Modifier
                .height(608.dp)
        ){
            GoogleMap(
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}