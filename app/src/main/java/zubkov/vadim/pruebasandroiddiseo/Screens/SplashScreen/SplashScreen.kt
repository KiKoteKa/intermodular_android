package zubkov.vadim.pruebasandroiddiseo.Screens.SplashScreen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import zubkov.vadim.pruebasandroiddiseo.Navigation.Routes
import zubkov.vadim.pruebasandroiddiseo.ui.theme.Purple700

@Composable
fun SplashScreenOne(navigationController: NavHostController) {
    var animation by remember {
        mutableStateOf(false)
    }
    val alphaAnim = animateFloatAsState(
        targetValue = if(animation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )

    LaunchedEffect(navigationController){
        animation = true
        delay(3000)
        navigationController.popBackStack()
        navigationController.navigate(Routes.Login.route)
    }

    Main(alphaAnim.value)
}

@Composable
fun Main(alphaAnim: Float){
    Box(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else Purple700)
            .fillMaxSize()
            .alpha(alphaAnim),
        contentAlignment = Alignment.Center
    ) {
        Box(
            Modifier
                .height(250.dp)
                .width(250.dp)
        ){
            Image(
                painterResource(zubkov.vadim.pruebasandroiddiseo.R.drawable.fotoi_inicio),
                contentDescription = stringResource(id = zubkov.vadim.pruebasandroiddiseo.R.string.iconoDescripcion),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

