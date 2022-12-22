package zubkov.vadim.pruebasandroiddiseo.Screens.Logins

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun LoginScreenOne(){
    var state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AnimacionInicial(state)
    }
}

@Composable
fun AnimacionInicial(state: MutableTransitionState<Boolean>) {
    AnimatedVisibility(
        visibleState = state,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = 3000,
                easing = LinearEasing
            )
        ) + slideInHorizontally(
            animationSpec = tween(
                durationMillis = 3000,
                easing = LinearOutSlowInEasing
            )
        ),
        exit = fadeOut(
            animationSpec = tween(
                durationMillis = 2000,
                easing = LinearEasing
            )
        )
    ) {
        Text(
            text = "Bienvenido a {Titulo Programa}",
            fontSize = 23.sp,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight(2),
            fontStyle = FontStyle(2),
            color = MaterialTheme.colors.primary
        )
    }

    var stateC = remember {
        MutableTransitionState(false).apply {
            state.targetState = false
            this.targetState = true
        }
    }

    AnimacionSecundaria(state = stateC)
}

@Composable
fun AnimacionSecundaria(state: MutableTransitionState<Boolean>){
    AnimatedVisibility(
        visibleState = state,
        enter = fadeIn(
            animationSpec = tween(
                delayMillis = 5300,
                durationMillis = 2000,
                easing = LinearEasing
            )
        ) + slideInHorizontally(
            animationSpec = tween(
                durationMillis = 3000,
                easing = LinearOutSlowInEasing
            )
        )
    ) {
        Column(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Aqui ira el login",
                fontSize = 23.sp,
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight(2),
                fontStyle = FontStyle(2),
                color = MaterialTheme.colors.primary
            )
        }
    }
}