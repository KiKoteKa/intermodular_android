package zubkov.vadim.pruebasandroiddiseo.Screens.SplashScreen

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import zubkov.vadim.pruebasandroiddiseo.Navigation.Routes

@Composable
fun SplashScreenOne(navigationController: NavHostController) {
    Text("SplashScreen")
    LaunchedEffect(navigationController){
        delay(2000)
        navigationController.navigate(Routes.Login.route)
    }
}