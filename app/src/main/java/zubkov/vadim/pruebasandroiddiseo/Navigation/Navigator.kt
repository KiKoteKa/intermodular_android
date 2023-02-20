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
import zubkov.vadim.pruebasandroiddiseo.GlobalViewModel
import zubkov.vadim.pruebasandroiddiseo.Screens.Rutas.HomeScreenOne
import zubkov.vadim.pruebasandroiddiseo.Screens.Logins.LoginScreenOne
import zubkov.vadim.pruebasandroiddiseo.Screens.MapScreen.MapaGoogle
import zubkov.vadim.pruebasandroiddiseo.Screens.Registers.RegisterScreenOne
import zubkov.vadim.pruebasandroiddiseo.Screens.SplashScreen.SplashScreenOne
import zubkov.vadim.pruebasandroiddiseo.Screens.Perfil.Components.PerfilUsuario
import zubkov.vadim.pruebasandroiddiseo.Screens.Perfil.UsersScreen
import zubkov.vadim.pruebasandroiddiseo.Screens.Rutas.AddRutaScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomNavigator() {
    val globalViewModel = GlobalViewModel()
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = Routes.SplashScreen.route) {
        composable(route = Routes.SplashScreen.route){
            SplashScreenOne(navigationController = navigationController)
        }
        composable(route = Routes.Login.route) {
            LoginScreenOne(navigationController = navigationController,globalViewModel)
        }
        composable(route = Routes.Register.route) {
            RegisterScreenOne(navigationController = navigationController,globalViewModel)
        }
        composable(route = Routes.Home.route) {
            HomeScreenOne(navigationController = navigationController,globalViewModel)
        }
        composable(route = Routes.NewRuta.route) {
            AddRutaScreen(navigationController = navigationController,globalViewModel)
        }
        composable(route = Routes.Users.route) {
            UsersScreen(navigationController = navigationController,globalViewModel)
        }
        composable(route = Routes.Gmap.route) {
            MapaGoogle(navigationController = navigationController,globalViewModel)
        }
        composable(route = Routes.Card.route,
            arguments = listOf(navArgument("cardId") { type = NavType.IntType })) {
            CardExtended(navigationController = navigationController,globalViewModel,it.arguments?.getInt("cardId") ?: 0)
        }
        composable(route = Routes.UserDetail.route,
            arguments = listOf(navArgument("userId") { type = NavType.IntType })) {
            PerfilUsuarioDetalle(navigationController = navigationController,
             it.arguments?.getInt("userId") ?: 0,
                globalViewModel)
        }
        composable(route = Routes.User.route,
            arguments = listOf(navArgument("userId") { type = NavType.IntType },
            navArgument("mostrarAtras") { type = NavType.BoolType })) {
            PerfilUsuario(navigationController = navigationController,globalViewModel,
                StaticData().getUsuarios().get(it.arguments?.getInt("userId") ?: 0),
                it.arguments?.getBoolean("mostrarAtras") ?: true)
        }
    }
}