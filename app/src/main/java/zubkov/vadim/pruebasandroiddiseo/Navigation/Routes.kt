package zubkov.vadim.pruebasandroiddiseo.Navigation

sealed class Routes(val route: String) {
    object SplashScreen: Routes("splashscreen")
    object Login: Routes("login")
    object Register: Routes("register")
    object Home: Routes("home")
}
