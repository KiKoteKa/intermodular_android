package zubkov.vadim.pruebasandroiddiseo.Screens.Homes.Models

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import zubkov.vadim.pruebasandroiddiseo.Screens.Homes.Boton


@Composable
fun DialogoNuevaRuta(
    show:Boolean,
    onDismiss: () -> Unit)
{

    Dialog(
        onDismissRequest = { onDismiss()},
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = true),

        ){
        Surface(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.wrapContentSize(),
            color = Color(0xFFD1D5E1)
        ) {

            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
                    .background(Color.White)
                    .height(530.dp)
                    .border(border = BorderStroke(0.3.dp, Color.Black)),

                ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Datos Nueva Ruta", fontSize = 25.sp)
                }

                Spacer(modifier = Modifier.height(50.dp))
                IntroducirTexto(textoDefecto = "FirstName", num = 1)
                IntroducirTexto(textoDefecto = "LastName", num = 2)
                IntroducirTexto(textoDefecto = "Email", num = 3)
                Boton("Añadir Ruta")
            }
        }
    }
}


@Composable
fun IntroducirTexto(textoDefecto : String,num:Int, textoEjemplo : String = "", backgroundColor : Color = Color.LightGray) {

    Row(  modifier = Modifier
        .height(65.dp)
        .fillMaxWidth()
        .background(MaterialTheme.colors.background)
        .padding(25.dp, 2.dp),

        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        var text by remember { mutableStateOf("") }

        OutlinedTextField(
            modifier = Modifier
                .width(350.dp),

            value = text,
            onValueChange = {},
            label = { Text(text = textoDefecto) },
            placeholder = { Text(text = textoEjemplo) },
            singleLine = true,

            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color(0xFFf7fafb),
                focusedIndicatorColor = MaterialTheme.colors.secondary,
            ),
        )
    }
}