package zubkov.vadim.pruebasandroiddiseo.Components

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

import zubkov.vadim.pruebasandroiddiseo.Dto.StaticData
import zubkov.vadim.pruebasandroiddiseo.GlobalViewModel
import zubkov.vadim.pruebasandroiddiseo.Model.Ruta
import zubkov.vadim.pruebasandroiddiseo.Screens.Rutas.Components.CardExtendedViewModel
import zubkov.vadim.pruebasandroiddiseo.Screens.Rutas.Models.CorazonFavorito
import java.text.SimpleDateFormat

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardExtended(navigationController: NavHostController,globalViewModel: GlobalViewModel, idRuta : Int){
    val cardViewModel = CardExtendedViewModel()
    val ruta = StaticData().getRuta(idRuta)
    val tarjetaDeUsuario = globalViewModel.usuarioRegistrado.value!!.id == ruta.usuarioPublicado.id
    /*
    val showDialog by cardViewModel.showConfirmarBorrado.observeAsState(initial = false)
    if (showDialog == true) {
        DialogoEliminar({cardViewModel.confirmarBorrado(idRuta,navigationController) },
            {cardViewModel.cerrarConfirmarBorrado() })

    }*/
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = ruta.nombre,
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
                },

                actions = {
                    if (!tarjetaDeUsuario) {
                        CorazonFavorito()
                    }else{
                        val interactionSource = MutableInteractionSource()
                        Icon(
                            imageVector = Icons.Outlined.Close,
                            contentDescription = "favorito",
                            tint = Color.LightGray,
                            modifier = Modifier
                                .scale(scale = 1f)
                                .size(size = 24.dp)
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = null
                                ) { /*cardViewModel.openConfirmarBorrado()*/ }
                        )
                    }
                }
            )
        },

        content = {
            CardExtendedComp(navigationController,ruta)
        }
    )
}



@Composable
fun CardExtendedComp(navigationController: NavHostController,ruta:Ruta)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .verticalScroll(rememberScrollState())
    ) {
            Carousel(ruta.imagenes)
            Spacer(modifier = Modifier.height(16.dp))
            CardInfo(navigationController,ruta)
    }
}

@Composable
fun ImageRuta(image:Int){
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(346.dp),
        painter = painterResource(image),
        alignment = Alignment.CenterStart,
        contentDescription = "",
        contentScale = ContentScale.Crop
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Carousel(imagenes:List<Int>) {
    val slideImage = remember { mutableStateOf(imagenes.get(0)) }
    val pagerState = rememberPagerState(0)
    HorizontalPager(count = imagenes.size, state = pagerState) { page ->
        when(page)  {
            else -> slideImage.value = imagenes.get(page)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(346.dp),
                painter = painterResource(slideImage.value),
                alignment = Alignment.CenterStart,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
    }

    Spacer(modifier = Modifier.padding(4.dp))

    DotsIndicator(
        totalDots = imagenes.size,
        selectedIndex = pagerState.currentPage,
        selectedColor = Color.White,
        unSelectedColor = MaterialTheme.colors.primaryVariant
    )
}

@Composable
fun DotsIndicator(
    totalDots : Int,
    selectedIndex : Int,
    selectedColor: Color,
    unSelectedColor: Color,
){

    LazyRow(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()

    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(selectedColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

@Composable
fun CardInfo(navigationController: NavHostController, ruta: Ruta) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            HeaderCard(navigationController,ruta)
            MidCard(ruta)
            BottomCard(ruta)
        }
    }
}

@Composable
fun HeaderCard(navigationController: NavHostController,ruta:Ruta){

    Spacer(modifier = Modifier.height(8.dp))

    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp, 8.dp, 0.dp, 0.dp)) {

        Box(modifier = Modifier.padding(0.dp),) {
            ImagenUsuario(navigationController,ruta)
        }

        Text(
            text = ruta.usuarioPublicado.nick,
            modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp),
            color = Color.White,
            style = MaterialTheme.typography.caption,

            )

        Text(
            text = "   |   " + SimpleDateFormat("dd/MM/yyyy").format(ruta.fecha),
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp),
            color = Color.White,
            style = MaterialTheme.typography.overline
        )

    }

}

@Composable
fun MidCard(ruta:Ruta){
    Spacer(modifier = Modifier.height(24.dp))
    Title("Descripción")
    Spacer(modifier = Modifier.height(16.dp))
    Text(
        text = ruta.descripcion,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        color = Color.White,
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Start
    )
}

@Composable
fun BottomCard(ruta:Ruta){
    Spacer(modifier = Modifier.height(24.dp))
    Title(title = "Info Ruta")
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MiniInfoCard(title = "Distancia", value = "${ruta.distancia} km")
        MiniInfoCard(title = "Dificultad", value = ruta.dificultad.toString())
        MiniInfoCard(title = "Actividad", value = StaticData().getCategoria(ruta.categoria))
    }
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 0.dp, 0.dp),
        color = Color(0xFFF5CAC9),
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.W600,
        textAlign = TextAlign.Start
    )
}

@Composable
fun ImagenUsuario(navigationController: NavHostController,ruta:Ruta) {
    Image(
        painter = painterResource(ruta.usuarioPublicado.imagen),
        contentDescription = "Imagen",
        modifier = Modifier
            .size(40.dp)
            .size(40.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
            .clickable { navigationController.navigate("user/${ruta.usuarioPublicado.id}/true") }
    )
}

@Composable
fun MiniInfoCard(title: String, value: String) {
    Box(
        modifier = Modifier
            .size(90.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFFFFFFF))
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.wrapContentWidth()
        ) {
            Text(
                text = value,
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF1E3054),
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Gray,
                style = MaterialTheme.typography.overline,
                textAlign = TextAlign.Center
            )
        }
    }
}

/*
@Composable
@ExperimentalFoundationApi
fun DialogoEliminar(
    onSuccess: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = {onDismiss},

        title = {
            Text("¿Estás seguro que deseas borrar esta ruta?")
        },
        text = {
            Text("Esta acción no es reversible. ¿Estás seguro que deseas continuar?")
        },
        confirmButton = {
            Row(modifier = Modifier.padding(0.dp,0.dp,55.dp,10.dp)) {
                Button(
                    onClick = {onDismiss},
                ) {
                    Text("Cancelar")
                }
            }
        },
        dismissButton = {
            Row(
                modifier = Modifier.padding(0.dp,0.dp,0.dp,0.dp)) {
                Button(
                    onClick = {onSuccess},
                ) {
                    Text("Borrar")
                }
            }
        },
    )
}
*/

@Composable
private fun DialogoEliminar(
    onSuccess: () -> Unit,
    onDismiss: () -> Unit,)
{
    Dialog(
        onDismissRequest = { onDismiss()},
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .background(Color.White)
                .height(290.dp)
                .border(border = BorderStroke(0.3.dp, Color.Black)),

            ) {

            Spacer(modifier = Modifier.height(10.dp))
            Text("¿Estás seguro que deseas borrar esta ruta?")
            Spacer(modifier = Modifier.height(30.dp))
            Text("Esta acción no es reversible. ¿Estás seguro que deseas continuar?")
            Spacer(modifier = Modifier.height(10.dp))
            Row(){
                Button(
                    onClick = {onSuccess},
                ) {
                    Text("Borrar")
                }
                Button(
                        onClick = {onDismiss},
                ) {
                Text("Cancelar")
            }

            }
        }
    }
}