package zubkov.vadim.pruebasandroiddiseo.Screens.Rutas.Models

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zubkov.vadim.pruebasandroiddiseo.Dto.StaticData
import zubkov.vadim.pruebasandroiddiseo.Screens.Rutas.FilterViewModel

const val maxFiltroDistancia = 300f

@Composable
fun Buscador(filter:FilterViewModel){
    Column() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(0.dp, 10.dp).background(MaterialTheme.colors.background)
        ) {
            FiltroTexto(filter)
        }
    }
}

fun Filtrar(filter:FilterViewModel){

    var listRutasMostrar = StaticData().getRutas()
    //Buscador
    var textoBusca = filter.text.value?.text?.uppercase()
    if (textoBusca != null)
    {
        if (filter.switchBusqueda.value == "Ruta") {
            listRutasMostrar = listRutasMostrar.filter { it.nombre.uppercase().contains(textoBusca) }
        } else {
            listRutasMostrar = listRutasMostrar.filter { it.usuarioPublicado.nick.uppercase().contains(textoBusca)}
        }
    }

    //Filtros
    if (filter.filtroDificultad.value != null) {
        listRutasMostrar = listRutasMostrar.filter {
            it.dificultad >= filter.filtroDificultad.value!!.start &&
                    it.dificultad <= filter.filtroDificultad.value!!.endInclusive
        }
    }
    /*
    if (filter.filtroEstrellas.value != null) {
        listRutasMostrar = listRutasMostrar.filter {
            it.valoracion >= filter.filtroEstrellas.value!!.start &&
                    it.valoracion <= filter.filtroEstrellas.value!!.endInclusive
        }
    }
    */
    if (filter.filtroDistancia.value != null) {
        var limiteFiltroDistancia = filter.filtroDistancia.value!!.endInclusive
        if (limiteFiltroDistancia == maxFiltroDistancia) limiteFiltroDistancia = 999999f;
        listRutasMostrar = listRutasMostrar.filter {
            it.distancia >= filter.filtroDistancia.value!!.start.toInt() &&
                    it.distancia <= limiteFiltroDistancia.toInt()
        }
    }

    listRutasMostrar = listRutasMostrar.filter {
        filter.filtroActividades.value!!.contains(StaticData().getCategoria(it.categoria))
    }

    filter.updateListado(listRutasMostrar)
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
            color = Color.White,
        ) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.primaryVariant,
            unfocusedBorderColor = MaterialTheme.colors.secondary
        ),
        shape = CircleShape,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp,10.dp,20.dp,0.dp),
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
                    tint = MaterialTheme.colors.secondary,

                )
            }
        },
    )

}

@Composable
fun Filtros(filter: FilterViewModel)
{
    var mostrarFiltro by remember{ mutableStateOf(false) }
    var color by remember{ mutableStateOf(Color.LightGray)}
    val counter by filter.countListado.observeAsState(initial = 0)

    Column() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(20.dp,0.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(18.dp),
                elevation = 4.dp,
                modifier = Modifier
                    .wrapContentSize()
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .clip(shape = RoundedCornerShape(18.dp))
                        .background(MaterialTheme.colors.secondary)
                        .clickable {
                            if (mostrarFiltro) {
                                mostrarFiltro = false
                                color = Color.LightGray
                                Filtrar(filter)
                            } else {
                                color = Color(0xFFCdca28)
                                mostrarFiltro = true
                            }
                        }
                ) {
                    Text(
                        text = "     Filtros     ",
                        fontSize = 14.sp,
                        color = Color.White,
                        modifier = Modifier.padding(
                            vertical = 12.dp,
                            horizontal = 16.dp,
                        )
                    )
                }
            }

            Text("Resultados: $counter")
            SwitchBusqueda(filter)
        }
        if (mostrarFiltro) {
            Separador()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
            ) {
                TituloFiltros("Dificultad")
                SliderDificultad(filter)
                Separador()
                TituloFiltros("Distancia")
                SliderDistancia(filter)
                Separador()
                TituloFiltros("Actividad")
                Actividades(filter)
            }
        }
    }
}

@Composable
fun TituloFiltros(texto:String){
    Row(modifier = Modifier
        .fillMaxWidth(),  horizontalArrangement = Arrangement.Center) {
        Column() {
            Text(text = texto,
                modifier = Modifier.padding(0.dp,2.dp,0.dp,0.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SliderDificultad(filter:FilterViewModel){
    val sliderValues: ClosedFloatingPointRange<Float> by filter.filtroDificultad.observeAsState(initial = 0f..4f)

    Column() {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp, 0.dp)) {
            RangeSlider(
                values= sliderValues,
                onValueChange = { sliderValues_ ->
                    filter.updateFiltroDificultad(sliderValues_)
                },
                valueRange = 0f..4f,
                steps = 3
            )
        }
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth().padding(30.dp,0.dp)
    ){
        Text(text = StaticData().getDificultad(sliderValues.start.toInt()))
        Text(text = StaticData().getDificultad(sliderValues.endInclusive.toInt()))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SliderDistancia(filter:FilterViewModel){
    val sliderValues: ClosedFloatingPointRange<Float> by filter.filtroDistancia.observeAsState(initial = 0f..maxFiltroDistancia)

    Column() {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp, 0.dp)) {
            RangeSlider(
                values= sliderValues,
                onValueChange = { sliderValues_ ->
                    filter.updateFiltroDistancia(sliderValues_)
                },
                valueRange = 0f..maxFiltroDistancia,
            )
        }
    }
    var limiteDistancia = sliderValues.endInclusive.toInt().toString()
    if (sliderValues.endInclusive == maxFiltroDistancia) limiteDistancia = limiteDistancia + "+"
    Row(horizontalArrangement = Arrangement.Center,modifier = Modifier.fillMaxWidth()) {
        Text(text = "De ${sliderValues.start.toInt()} a ${limiteDistancia} km")
    }
}

@Composable
fun Actividades(filter:FilterViewModel){
    LazyHorizontalGrid(
        rows = GridCells.Adaptive(70.dp),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(StaticData().categorias) { item ->
            BotonActividades(item,filter)
        }
    }

}

@Composable
fun BotonActividades(texto: String,filter:FilterViewModel){
    var selected by remember { mutableStateOf(filter.activeActivity(texto)) }
    val backgroundColor = if (selected) MaterialTheme.colors.secondary else Color.LightGray
    val contentColor = if (selected) Color.White else Color.Black

    Button(
        onClick = {
            selected = !selected
            if (selected) {
                filter.addActivity(texto)
            }else{
                filter.removeActivity(texto)
            }},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
        ),
        modifier = Modifier
            .padding(6.dp)
            .size(125.dp, 45.dp),
        shape = RoundedCornerShape(40),
        border = BorderStroke(2.dp, MaterialTheme.colors.secondary)
    ) {
        Text(text = texto, fontSize = 11.sp)
    }
}