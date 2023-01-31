package zubkov.vadim.pruebasandroiddiseo.Screens.Homes

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import zubkov.vadim.pruebasandroiddiseo.Model.Ruta
import zubkov.vadim.pruebasandroiddiseo.Screens.Homes.Models.MainCard
import zubkov.vadim.pruebasandroiddiseo.Screens.Models.BottomBarContent
import zubkov.vadim.pruebasandroiddiseo.Screens.Models.TopBarContent
import java.util.*


private var rutasFiltradas : MutableList<Ruta> = mutableListOf()

private val rutas : List<Ruta> = listOf(
    Ruta("Ruta de Ordesa",1,13.2,550.0,1,1,"Paco",3.6,1),
    Ruta("Ruta de Pepito",1,13.2,550.0,2,1,"Juan",3.6,1),
    Ruta("Ruta de Valencia",1,13.2,550.0,3,1,"Alvaro",3.6,1),
    Ruta("Ruta de Castellon",1,13.2,550.0,3,1,"Paco",3.6,1),
    Ruta("Ruta de Condesa",2,10.2,850.0,2,1,"Maria",4.6,1)
)


@Composable
fun HomeScreenOne(navigationController: NavHostController) {
    rutasFiltradas = rutas.toMutableList();
    Scaffold(
        topBar = { TopBarContent() },
        bottomBar = { BottomBarContent(navigationController) }
    ) {
        EstructuraVentanaHome()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun EstructuraVentanaHome() {
    val checkRuta = remember { mutableStateOf(true) }
    val checkUsuario = remember { mutableStateOf(false) }
    var textoBusqueda by remember{ mutableStateOf(TextFieldValue("")) }
    val keyboardController = LocalSoftwareKeyboardController.current
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .border(border = BorderStroke(0.3.dp, Color.Black)))
    {
        Spacer(modifier = Modifier.height(10.dp))
        Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
            Titulo("Panel de Rutas")
        }
        Separador()
        Row() {
            Checkbox(
                checked = checkRuta.value,
                onCheckedChange = { checkRuta.value = it; checkUsuario.value = false}
            )


            Text(text = "Ruta")
            Checkbox(
                checked = checkUsuario.value,
                onCheckedChange = { checkUsuario.value = it;checkRuta.value = false}
            )
            Text(text = "Usuario")
        }
        Row(){
            OutlinedTextField(
                value = textoBusqueda,
                onValueChange = {textoBusqueda = it},
                placeholder = { Text(
                    text = "Buscar",
                    color = Color(0xFFDAD3C8),
                ) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFDAD3C8),
                    unfocusedBorderColor = Color(0xFFDAD3C8)
                ),
                shape = CircleShape,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        keyboardController?.hide()
                        if (textoBusqueda.text == "")
                        {
                            rutasFiltradas = rutas.toMutableList()
                        }else {
                            if (checkRuta.value) {
                                rutasFiltradas = rutas.filter { it.nombre.uppercase()
                                    .contains(textoBusqueda.text.uppercase()) }
                                    .toMutableList()
                            } else {
                                rutasFiltradas =
                                    rutas.filter { it.usuarioPublicado.uppercase().contains(textoBusqueda.text.uppercase()) }
                                        .toMutableList()
                            }
                        }
                    }
                ),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            keyboardController?.hide()
                            if (textoBusqueda.text == "")
                            {
                                rutasFiltradas = rutas.toMutableList()
                            }else {
                                if (checkRuta.value) {
                                    rutasFiltradas = rutas.filter { it.nombre.uppercase()
                                        .contains(textoBusqueda.text.uppercase()) }
                                        .toMutableList()
                                } else {
                                    rutasFiltradas =
                                        rutas.filter { it.usuarioPublicado.uppercase().contains(textoBusqueda.text.uppercase()) }
                                            .toMutableList()
                                }
                            }
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
        Text(text = "Filtros")
        Row(){
            BotonFiltros(texto = "1*")
            BotonFiltros(texto = "2*")
            BotonFiltros(texto = "3*")
            BotonFiltros(texto = "4*")
            BotonFiltros(texto = "5*")
        }

        Separador()
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
        ) {
            for (i in rutasFiltradas) {
                MainCard(i)
            }
            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(60.dp))
        }
    }
}

@Composable
fun BotonFiltros(texto:String,){

    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White,
            contentColor = MaterialTheme.colors.secondary,
        ),
        modifier = Modifier
            .padding(6.dp)
            .size(65.dp, 50.dp),
        shape = RoundedCornerShape(40),
        border = BorderStroke(2.dp, MaterialTheme.colors.secondary)
    ) {
        Text(text = texto)
    }
}

@Composable
fun Texto(valor:String,texto:String,icono: ImageVector = Icons.Default.Person){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(30.dp, 2.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp / 3), horizontalArrangement = Arrangement.Start) {
                Icon(imageVector = icono, contentDescription = texto, tint = MaterialTheme.colors.secondary)
                Text(
                    text = texto,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(modifier = Modifier.width(LocalConfiguration.current.screenWidthDp.dp*2/ 3), horizontalArrangement = Arrangement.End) {
                Text(
                    color = Color.Gray,
                    text = valor,
                    fontSize = 16.sp
                )
            }
        }
        Divider(color = Color.LightGray, modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp, 10.dp)
            .height(1.3.dp))
    }
}

@Composable
fun TextoPrincipal(texto:String){
    Text(text = texto,
        modifier = Modifier.padding(0.dp,20.dp,0.dp,10.dp),
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun Titulo(texto:String){
    Text(text = texto,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color=MaterialTheme.colors.secondary
    )
}

@Composable
fun BotonLogOut(){
    IconButton(onClick = { })
    {
        Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = "LogOut", tint = MaterialTheme.colors.secondary)
    }
}

@Composable
fun Separador(){
    Divider(color = MaterialTheme.colors.secondary, modifier = Modifier
        .fillMaxWidth()
        .padding(0.dp, 10.dp, 0.dp, 20.dp)
        .height(1.dp))
}


@Composable
fun ImagenRedonda(idFoto:Int) {
    Image(
        painter = painterResource(idFoto),
        contentDescription = "Imagen",
        modifier = Modifier
            .size(150.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
    )
}

@Composable
fun SearchAppBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.primary
    ) {
        TextField(modifier = Modifier
            .fillMaxWidth(),
            value = text,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    text = "Search here...",
                    color = Color.White
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = Color.White
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        } else {
                            onCloseClicked()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        tint = Color.White
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = Color.White.copy(alpha = ContentAlpha.medium)
            ))
    }
}

@Composable
fun dropDownMenuDificultad(){
    var expanded by remember{ mutableStateOf(false)}
    var list = listOf("Facil", "Normal", "Dificil")
    var selectedItem by remember { mutableStateOf("")}
    
    var textFiledSize by remember { mutableStateOf(Size.Zero) }
    
    val icon = if(expanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }
    
    Column(modifier = Modifier.padding(20.dp)) {
        
        OutlinedTextField(value = selectedItem, onValueChange = {selectedItem = it},
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                textFiledSize = coordinates.size.toSize()
            },

        label = {Text(text = "Select Items")},
        trailingIcon = {
            Icon(icon, "", Modifier.clickable{expanded =! expanded})
        }

        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {expanded = false},
            modifier = Modifier.width(with(LocalDensity.current){textFiledSize.width.toDp()})
        ){

            list.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedItem = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }

        }


    }

}



