package zubkov.vadim.pruebasandroiddiseo.Navigation

sealed class Routes(val route: String) {
    object SplashScreen: Routes("splashscreen")
    object Login: Routes("login")
    object Register: Routes("register")
    object Home: Routes("home")
    object Card: Routes("card/{cardId}")
    object Gmap: Routes("gmap")
    object Users: Routes("users")
    object NewRuta: Routes("newruta")
    object UserDetail: Routes("userdetail/{userId}")
    object User: Routes("user/{userId}/{mostrarAtras}")
}
