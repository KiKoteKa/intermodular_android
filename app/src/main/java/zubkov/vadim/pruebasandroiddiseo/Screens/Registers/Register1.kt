package zubkov.vadim.pruebasandroiddiseo.Screens.Registers

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import zubkov.vadim.pruebasandroiddiseo.GlobalViewModel
import zubkov.vadim.pruebasandroiddiseo.Navigation.Routes
import zubkov.vadim.pruebasandroiddiseo.R
import zubkov.vadim.pruebasandroiddiseo.Screens.Logins.*
import zubkov.vadim.pruebasandroiddiseo.Screens.Logins.MainLogin

var comprobacionRapida = false

@Composable
fun RegisterScreenOne(navigationController: NavHostController,globalViewModel: GlobalViewModel) {
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
        InicioRegister(state,navigationController)
    }
}

@Composable
fun InicioRegister(state: MutableTransitionState<Boolean>, navigationController: NavHostController){
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painterResource(R.drawable.background),
            contentDescription = stringResource(id = R.string.icono_descripcion),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize(),
            alpha = if (isSystemInDarkTheme()) 0.5F else 0.8f
        )
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            MainRegister(state = state,navigationController)
        }
    }
}

@Composable
fun MainRegister(state: MutableTransitionState<Boolean>, navigationController: NavHostController){
    AnimatedVisibility(
        visibleState = state,
        enter = fadeIn(
            animationSpec = tween(
                delayMillis = 400,
                durationMillis = 1000,
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
            TituloRegister()
            Spacer(modifier = Modifier.padding(15.dp))
            Box(
                modifier = Modifier.width(275.dp)
            ){
                Column {
                    EmailRegister()
                    Spacer(modifier = Modifier.padding(10.dp))
                    ContrasenyaRegister()
                    Spacer(modifier = Modifier.padding(10.dp))
                    ContrasenyaRepetirRegister()
                    Spacer(modifier = Modifier.padding(15.dp))
                    TermsAndConditions(navigationController)
                    Spacer(modifier = Modifier.padding(5.dp))
                    ButtonRegister(navigationController)
                }
            }
        }
    }
}

@Composable
fun TituloRegister(){
    Text(
        text = stringResource(id = R.string.titulo),
        fontSize = 23.sp,
        fontFamily = FontFamily.Default,
        color = Color(0xFFDAD3C8)
    )
}

@Composable
fun EmailRegister(){
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
fun ContrasenyaRegister(){
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
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun ContrasenyaRepetirRegister(){
    var texto by remember{ mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = texto,
        onValueChange = {texto = it},
        placeholder = { Text(
            text = "Repetir Contraseña",
            color = Color(0xFFDAD3C8)
        ) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFFDAD3C8),
            unfocusedBorderColor = Color(0xFFDAD3C8)
        ),
        shape = CircleShape,
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
fun ButtonRegister(navigationController: NavHostController) {
    val mContext = LocalContext.current

    Button(
        onClick = {
            if (comprobacionRapida){
                navigationController.popBackStack()
                navigationController.navigate(Routes.Home.route)
            } else {
                Toast.makeText(mContext,"No acepto los terminos y condiciones", Toast.LENGTH_SHORT).show()
            }
        },
        shape = CircleShape,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF7B7457))
    ){
        Text("Register")
    }
}

@Composable
fun TermsAndConditions(navigationController: NavHostController) {
    var confirmar by remember {
        mutableStateOf(false)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = confirmar,
            onCheckedChange = {
                confirmar = !confirmar
                comprobacionRapida = confirmar
            }
        )
        androidx.compose.foundation.text.ClickableText(
            text = AnnotatedString("Aceptar los terminos de servicios y condiciones para el programa"),
            onClick = {

            },
            style = TextStyle(
                color = Color(0xFFDAD3C8)
            )
        )
    }
}