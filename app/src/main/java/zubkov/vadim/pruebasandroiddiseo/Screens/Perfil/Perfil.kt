package zubkov.vadim.pruebasandroiddiseo.Screens.Perfil

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import zubkov.vadim.pruebasandroiddiseo.Dto.StaticData
import zubkov.vadim.pruebasandroiddiseo.GlobalViewModel
import zubkov.vadim.pruebasandroiddiseo.Navigation.Routes
import zubkov.vadim.pruebasandroiddiseo.Screens.Scaffold.CustomBottomBar
import zubkov.vadim.pruebasandroiddiseo.Screens.Scaffold.TopBarContent
import zubkov.vadim.pruebasandroiddiseo.Screens.Perfil.Components.PerfilUsuario

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UsersScreen(navigationController: NavHostController,globalViewModel: GlobalViewModel) {
    Scaffold(
        topBar = { TopBarContent() },
        bottomBar = { CustomBottomBar(navigationController) },
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
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize().border(border = BorderStroke(0.3.dp, Color.Black)))
            {
                if (globalViewModel.usuarioRegistrado == null){
                    Text("Haz login para ver tu perfil")
                }else {
                    PerfilUsuario(
                        navigationController,
                        globalViewModel,
                        globalViewModel.usuarioRegistrado.value!!
                    )
                }
            }
        }
    }
}
