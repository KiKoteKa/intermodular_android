package zubkov.vadim.pruebasandroiddiseo.Screens.Logins

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.text.TextStyle
import zubkov.vadim.pruebasandroiddiseo.R

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
        Inicio(state)
    }
}

@Composable
fun Inicio(state: MutableTransitionState<Boolean>){
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painterResource(R.drawable.background),
            contentDescription = stringResource(id = R.string.iconoDescripcion),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize(),
            alpha = 0.5F
        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            AnimacionInicial(state = state)
        }
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
                easing = LinearOutSlowInEasing
            )
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "Bienvenido a {Titulo Programa}",
                fontSize = 23.sp,
                fontFamily = FontFamily.Default,
                color = Color(0xFFDAD3C8)
            )
        }
    }

    var stateC = remember {
        MutableTransitionState(false).apply {
            state.targetState = false
            this.targetState = true
        }
    }

    MainLogin(state = stateC)
}

@Composable
fun MainLogin(state: MutableTransitionState<Boolean>){
    AnimatedVisibility(
        visibleState = state,
        enter = fadeIn(
            animationSpec = tween(
                delayMillis = 5300,
                durationMillis = 2000,
                easing = LinearOutSlowInEasing
            )
        ) + slideInVertically(
            animationSpec = tween(
                delayMillis = 5300,
                durationMillis = 2000,
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
            Imagen_Login()
            Spacer(modifier = Modifier.padding(5.dp))
            Titulo()
            Spacer(modifier = Modifier.padding(15.dp))
            Box(
                modifier = Modifier.width(275.dp)
            ){
                Column {
                    Email()
                    Spacer(modifier = Modifier.padding(10.dp))
                    Contrasenya()
                    Spacer(modifier = Modifier.padding(10.dp))
                    ButtonLogin()
                }
            }
            Spacer(modifier = Modifier.padding(6.dp))
            ClickableText()
        }
    }
}

@Composable
fun Imagen_Login(){
    Card(
        Modifier
            .height(250.dp)
            .width(250.dp),
        shape = CircleShape,
    ){
        Image(
            painterResource(R.drawable.foto),
            contentDescription = stringResource(id = R.string.iconoDescripcion),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun Titulo(){
    Text(
        text = stringResource(id = R.string.titulo),
        fontSize = 23.sp,
        fontFamily = FontFamily.Default,
        color = Color(0xFFDAD3C8)
    )
}

@Composable
fun Email(){
    var texto by remember{ mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = texto,
        onValueChange = {texto = it},
        placeholder = { Text(
            text = "Email",
            color = Color(0xFFDAD3C8)
        ) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFFDAD3C8),
            unfocusedBorderColor = Color(0xFFDAD3C8)
        ),
        shape = CircleShape,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun Contrasenya(){
    var texto by remember{ mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = texto,
        onValueChange = {texto = it},
        placeholder = { Text(
            text = "Contraseña",
            color = Color(0xFFDAD3C8)
        ) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFFDAD3C8),
            unfocusedBorderColor = Color(0xFFDAD3C8)
        ),
        shape = CircleShape,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ButtonLogin(){
    Button(
        onClick = {

        },
        shape = CircleShape,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7B7457))
    ){
        Text("Login")
    }
}

@Composable
fun ClickableText(){
    Row(){
        ClickableText(
            text = AnnotatedString("Contraseña Olvidada"),
            onClick = {

            },
            style = TextStyle(
                color = Color(0xFFDAD3C8)
            )
        )
        Text(
            text = " | ",
            color = Color(0xFFDAD3C8)
        )
        ClickableText(
            text = AnnotatedString("Registrarse"),
            onClick = {

            },
            style = TextStyle(
                color = Color(0xFFDAD3C8)
            )
        )
    }

}