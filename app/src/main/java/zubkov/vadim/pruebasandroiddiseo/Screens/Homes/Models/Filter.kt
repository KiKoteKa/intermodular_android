package zubkov.vadim.pruebasandroiddiseo.Screens.Homes.Models

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zubkov.vadim.pruebasandroiddiseo.Dto.StaticData
import zubkov.vadim.pruebasandroiddiseo.Model.Ruta
import zubkov.vadim.pruebasandroiddiseo.Screens.Homes.*
import java.util.logging.Filter
const val maxFiltroDistancia = 300f

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
    if (filter.filtroEstrellas.value != null) {
        listRutasMostrar = listRutasMostrar.filter {
            it.valoracion >= filter.filtroEstrellas.value!!.start &&
            it.valoracion <= filter.filtroEstrellas.value!!.endInclusive
        }
    }

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
fun Filtros(filter: FilterViewModel)
{
    var mostrarFiltro by remember{ mutableStateOf(false) }
    var text by remember{ mutableStateOf("Mostrar Filtros") }
    ClickableText(
        text = AnnotatedString(text) ,
        onClick = {
            if (mostrarFiltro) {
                mostrarFiltro = false
                text = "Mostrar Filtros"
                Filtrar(filter)
            }else {
                mostrarFiltro = true
                text = "Ocultar Filtros"
            }
        })
    if (mostrarFiltro) {
        Separador()
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(480.dp)) {
            TituloFiltros("Estrellas")
            SliderRating(filter)
            Separador()
            TituloFiltros("Distancia")
            SliderDistancia(filter)
            Separador()
            TituloFiltros("Actividad")
            Actividades(filter)
        }
    }
}

@Composable
fun TituloFiltros(texto:String){
    Row(modifier = Modifier
        .fillMaxWidth(),  horizontalArrangement = Arrangement.Center) {
        Column() {
            Text(text = texto,
                modifier = Modifier.padding(10.dp,0.dp,0.dp,0.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SliderRating(filter:FilterViewModel){
    val sliderValues: ClosedFloatingPointRange<Float> by filter.filtroEstrellas.observeAsState(initial = 0f..5f)

    Column() {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp, 0.dp)) {
            RangeSlider(
                values= sliderValues,
                onValueChange = { sliderValues_ ->
                    filter.updateFiltroEstrellas(sliderValues_)
                },
                valueRange = 0f..5f,
                steps = 4
            )
        }
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
    Text(text = "De ${sliderValues.start.toInt()} a ${limiteDistancia} km")
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