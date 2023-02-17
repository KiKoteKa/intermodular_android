package zubkov.vadim.pruebasandroiddiseo.Navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import zubkov.vadim.pruebasandroiddiseo.Components.CardExtended
import zubkov.vadim.pruebasandroiddiseo.Components.PerfilUsuarioDetalle
import zubkov.vadim.pruebasandroiddiseo.Dto.StaticData
import zubkov.vadim.pruebasandroiddiseo.Screens.Rutas.HomeScreenOne
import zubkov.vadim.pruebasandroiddiseo.Screens.Logins.LoginScreenOne
import zubkov.vadim.pruebasandroiddiseo.Screens.MapScreen.MapaGoogle
import zubkov.vadim.pruebasandroiddiseo.Screens.Registers.RegisterScreenOne
import zubkov.vadim.pruebasandroiddiseo.Screens.Rutas.AddRuta
import zubkov.vadim.pruebasandroiddiseo.Screens.SplashScreen.SplashScreenOne
import zubkov.vadim.pruebasandroiddiseo.Screens.Perfil.Components.PerfilUsuario
import zubkov.vadim.pruebasandroiddiseo.Screens.Perfil.UsersScreen
import zubkov.vadim.pruebasandroiddiseo.Screens.Rutas.AddRutaScreen

@OptIn(ExperimentalFoundationApi::class)
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
        composable(route = Routes.NewRuta.route) {
            AddRutaScreen(navigationController = navigationController)
        }
        composable(route = Routes.Users.route) {
            UsersScreen(navigationController = navigationController)
        }
        composable(route = Routes.Gmap.route) {
            MapaGoogle(navigationController = navigationController)
        }
        composable(route = Routes.Card.route,
            arguments = listOf(navArgument("cardId") { type = NavType.IntType })) {
            CardExtended(navigationController = navigationController,it.arguments?.getInt("cardId") ?: 0)
        }
        composable(route = Routes.UserDetail.route,
            arguments = listOf(navArgument("userId") { type = NavType.IntType },
                navArgument("editable") { type = NavType.BoolType })) {
            PerfilUsuarioDetalle(navigationController = navigationController,it.arguments?.getInt("userId") ?: 0,it.arguments?.getBoolean("editable") ?: false)
        }
        composable(route = Routes.User.route,
            arguments = listOf(navArgument("userId") { type = NavType.IntType },
                navArgument("editable") { type = NavType.BoolType })) {
            PerfilUsuario(navigationController = navigationController,
                StaticData().getUsuarios().get(it.arguments?.getInt("userId") ?: 0),it.arguments?.getBoolean("editable") ?: false)
        }
    }
}