package zubkov.vadim.pruebasandroiddiseo.Screens.Rutas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import zubkov.vadim.pruebasandroiddiseo.Components.CardExtendedComp
import zubkov.vadim.pruebasandroiddiseo.Screens.Rutas.Models.AnimacionCorazon

@Composable
fun AddRutaScreen(navigationController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Añadir ruta",
                        modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h5
                    )
                },
                backgroundColor = MaterialTheme.colors.primary,
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp, 24.dp)
                            .clickable {
                                navigationController.navigateUp()
                            },
                        tint = MaterialTheme.colors.background
                    )
                }
            )
        },

        content = {
            AddRuta(navigationController)
        }
    )


}

@Composable
fun AddRuta(navigationController: NavHostController) {

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        nombreTextField()
        descripcionTextField()
        listaBotonesDificultad()
        apartadoDistanciaCategoria()
        guardarBoton()

    }
}

@Composable
fun nombreTextField() {
    var value by remember {mutableStateOf("")}
    OutlinedTextField(
        value = value,
        onValueChange = { newText ->
            value = newText
        },
        label = { Text("Nombre") },
        placeholder = { Text("Escriba el Nombre de la Ruta Aquí") },
        maxLines = 5,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    )
}


@Composable
fun descripcionTextField() {
    var value by remember {mutableStateOf("")}
    OutlinedTextField(
        value = value,
        onValueChange = { newText ->
            value = newText
        },
        label = { Text("Descripción") },
        placeholder = { Text("Escriba su Descripción de la Ruta Aquí") },
        maxLines = 5,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(160.dp)
    )
}

@Composable
fun listaBotonesDificultad() {

    Column(
        modifier = Modifier
            .padding(5.dp, 14.dp, 5.dp, 5.dp)
            .clip(shape = RoundedCornerShape(8.dp))
            .border(
                BorderStroke(1.dp, Color.Transparent)
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 8.dp, 0.dp, 10.dp)

        ) {
            Text(
                color = Color.Gray,
                text = "Dificultad")
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 0.dp)
                .border(
                    BorderStroke(
                        0.dp, Color.Transparent
                    )
                )
        ) {
            Button(onClick = {  }) {
                Text("Facil")
            }
            Button(onClick = { }) {
                Text("Moderado")
            }
            Button(onClick = { }) {
                Text("Difícil")
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 0.dp, 10.dp)
                .border(
                    BorderStroke(0.dp, Color.Transparent)
                )
        ) {
            Button(
                modifier = Modifier.padding(0.dp, 0.dp, 35.dp, 0.dp),
                onClick = { }) {
                Text("Muy Difícil")
            }
            Button(
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),
                onClick = {  }) {
                Text("Solo Expertos")
            }
        }

    }
}

@Composable
fun apartadoDistanciaCategoria() {
    var value by remember {mutableStateOf("")}

    Row (verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier.padding(5.dp,12.dp,5.dp,5.dp)){

        OutlinedTextField(
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            value = value,
            onValueChange = { newText ->
                value = newText
            },
            label = { Text("Distancia (km)") },
            modifier = Modifier.weight(1f)
        )

        Button(
            onClick = {},
            modifier = Modifier.padding(10.dp,10.dp,0.dp,0.dp)
                .weight(1f)
                .height(55.dp)
        ) {
            Text("Categoría")
        }
    }
}


@Composable
fun guardarBoton() {
        Button(
            onClick = {
                // Aquí se podría agregar la lógica para guardar la ruta de senderismo en una base de datos o en otro lugar
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Empezar a Grabar Ruta")
        }
    }