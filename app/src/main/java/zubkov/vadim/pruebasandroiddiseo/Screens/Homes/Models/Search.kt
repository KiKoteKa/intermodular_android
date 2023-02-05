package zubkov.vadim.pruebasandroiddiseo.Screens.Homes.Models

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zubkov.vadim.pruebasandroiddiseo.Screens.Homes.FilterViewModel

@Composable
fun Buscador(filter:FilterViewModel){
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(0.dp,10.dp)) {
        FiltroTexto(filter)
        SwitchBusqueda(filter)
    }
}


@Composable
fun SwitchBusqueda(filter: FilterViewModel) {
    Surface(
        shape = RoundedCornerShape(18.dp),
        elevation = 4.dp,
        modifier = Modifier
            .wrapContentSize()
    ) {
        Row(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(18.dp))
                .background(Color.LightGray)
        ) {
            TextSwitch(filter,"Ruta")
            TextSwitch(filter,"User")
        }
    }
}

@Composable
fun TextSwitch(filter: FilterViewModel,text:String){
    val selectedSwitch: String by filter.switchBusqueda.observeAsState(initial = "Ruta")

    Text(
        text = text,
        fontSize = 14.sp,
        color = Color.White,
        modifier = Modifier
            .clip(shape = RoundedCornerShape(18.dp))
            .clickable {
                filter.updateSwitch(text)
            }
            .background(
                if (text == selectedSwitch) {
                    MaterialTheme.colors.secondary
                } else {
                    Color.LightGray
                }
            )
            .padding(
                vertical = 12.dp,
                horizontal = 16.dp,
            ),
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FiltroTexto(filter:FilterViewModel){

    val keyboardController = LocalSoftwareKeyboardController.current
    val textoBusqueda: TextFieldValue by filter.text.observeAsState(initial = TextFieldValue(""))

    OutlinedTextField(
        value = textoBusqueda,
        onValueChange = {filter.updateText(it)},
        placeholder = { Text(
            text = "Buscar",
            color = Color(0xFFDAD3C8),
        ) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color(0xFFDAD3C8),
            unfocusedBorderColor = Color(0xFFDAD3C8)
        ),
        shape = CircleShape,
        modifier = Modifier
            .width(260.dp)
            .padding(5.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                Filtrar(filter)
            }
        ),
        trailingIcon = {
            IconButton(
                onClick = {
                    keyboardController?.hide()
                    Filtrar(filter)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = MaterialTheme.colors.secondary
                )
            }
        },
    )

}