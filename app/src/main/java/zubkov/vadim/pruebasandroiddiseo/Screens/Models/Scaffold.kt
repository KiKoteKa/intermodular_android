package zubkov.vadim.pruebasandroiddiseo.Screens.Models

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import zubkov.vadim.pruebasandroiddiseo.Navigation.Routes

@Composable
fun TopBarContent(){
    TopAppBar(
        backgroundColor = Color(0xFF3F826D)
    ) {
        Box(
            Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text("Titulo Programa")
        }
    }
}

@Composable
fun BottomBarContent(navigationController: NavHostController){
    BottomNavigation(
        backgroundColor = Color(0xFF3F826D)
    ) {
        BottomNavigationItem(
            selected = false,
            onClick = { navigationController.navigate(Routes.Home.route) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = ""
                )
            }
        )
        BottomNavigationItem(
            selected = false,
            onClick = { navigationController.navigate(Routes.Gmap.route) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = ""
                )
            }
        )
        BottomNavigationItem(
            selected = false,
            onClick = { navigationController.navigate(Routes.Users.route) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = ""
                )
            }
        )
    }
}