package zubkov.vadim.pruebasandroiddiseo.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import zubkov.vadim.pruebasandroiddiseo.Screens.Homes.HomeScreenOne
import zubkov.vadim.pruebasandroiddiseo.Screens.Logins.LoginScreenOne
import zubkov.vadim.pruebasandroiddiseo.Screens.MapScreen.MapaGoogle
import zubkov.vadim.pruebasandroiddiseo.Screens.Registers.RegisterScreenOne
import zubkov.vadim.pruebasandroiddiseo.Screens.SplashScreen.SplashScreenOne
import zubkov.vadim.pruebasandroiddiseo.Screens.Users.UsersScreen

@Composable
fun CustomNavigator() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = Routes.SplashScreen.route) {
        composable(route = Routes.SplashScreen.route){
            SplashScreenOne(navigationController = navigationController)
        }
        composable(route = Routes.Login.route) {
            LoginScreenOne(navigationController = navigationController)
        }
        composable(route = Routes.Register.route) {
            RegisterScreenOne(navigationController = navigationController)
        }
        composable(route = Routes.Home.route) {
            HomeScreenOne(navigationController = navigationController)
        }
        composable(route = Routes.Users.route) {
            UsersScreen(navigationController = navigationController)
        }
        composable(route = Routes.Gmap.route) {
            MapaGoogle(navigationController = navigationController)
        }
    }
}