package zubkov.vadim.pruebasandroiddiseo.Screens.Perfil.Components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import zubkov.vadim.pruebasandroiddiseo.Model.Usuario
import zubkov.vadim.pruebasandroiddiseo.R


@ExperimentalFoundationApi
@Composable
fun PerfilUsuario(navigationController: NavHostController, usuario: Usuario, perfilPropio:Boolean = false) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    Column(modifier = Modifier.fillMaxWidth().background(Color.LightGray)) {
        TopBar(
            name = usuario.nick,
            !perfilPropio,
            perfilPropio,
            navigationController
        )
        Spacer(modifier = Modifier.height(4.dp))
        ProfileSection(usuario)
        Spacer(modifier = Modifier.height(25.dp))
        ButtonSection(navigationController,modifier = Modifier.fillMaxWidth(),usuario,perfilPropio)
        Spacer(modifier = Modifier.height(25.dp))

        PostTabView(
            modifier = Modifier.background(MaterialTheme.colors.background),
            imageWithTexts = listOf(
                ImageWithText(
                    image = painterResource(id = R.drawable.iconorutas),
                    text = "Rutas"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.corazon),
                    text = "Likes"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.seguidos),
                    text = "Seguidos"
                ),
                ImageWithText(
                    image = painterResource(id = R.drawable.seguidores),
                    text = "Seguidores"
                ),
            )
        ) {
            selectedTabIndex = it
        }
        when(selectedTabIndex) {
            0 -> MostrarRutas(
                posts = listOf(),
                modifier = Modifier.fillMaxWidth()
            )
            1 -> MostrarRutasLikes(
                posts = listOf(),
                modifier = Modifier.fillMaxWidth()
            )
            2 -> MostrarSeguidos(
                posts = listOf(),
                modifier = Modifier.fillMaxWidth()
            )
            3 -> MostrarSeguidores(
                posts = listOf(),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    }


@Composable
fun TopBar(
    name: String,
    mostrarAtras : Boolean,
    mostrarlogout : Boolean,
    navigationController: NavHostController
) {
        Box(
            modifier = Modifier.fillMaxWidth().padding(5.dp)
        ) {
            if(mostrarAtras) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Black,
                    modifier = Modifier.size(24.dp)
                        .align(Alignment.CenterStart)
                        .clickable {
                            navigationController.navigateUp()
                        }
                )
            }

            Text(
                text = name,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )

            if (mostrarlogout) {
                IconButton(enabled = true,
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = { },
                ) {
                    Icon(
                        imageVector = Icons.Filled.ExitToApp,
                        contentDescription = "LogOut",
                        tint = Color.Black
                    )
                }
            }

    }
}

@Composable
fun ProfileSection(
    usuario: Usuario,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            RoundImage(
                image = painterResource(id = R.drawable.fotoperfil),
                modifier = Modifier
                    .size(100.dp)
                    .weight(3f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            StatSection(modifier = Modifier.weight(7f))
        }
    }
}

@Composable
fun RoundImage(
    image: Painter,
    modifier: Modifier = Modifier
) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape)
    )
}

@Composable
fun StatSection(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        ProfileStat(numberText = "100", text = "Rutas")
        ProfileStat(numberText = "100", text = "Seguidores")
        ProfileStat(numberText = "20", text = "Siguiendo")
    }
}

@Composable
fun ProfileStat(
    numberText: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = numberText,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = text)
    }
}

@Composable
fun ButtonSection(
    navigationController: NavHostController,
    modifier: Modifier = Modifier,
    usuario:Usuario,
    perfilPropio : Boolean
) {
    val minWidth = 175.dp
    val height = 35.dp
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
    ) {
        BotonUser(
            navigationController,
            usuario.id,
            perfilPropio,
            icon = Icons.Outlined.Person,
            text = "Datos  ",
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
        )
        if (!perfilPropio) {
            BotonUser(
                navigationController,
                usuario.id,
                perfilPropio,
                text = "Seguir  ",
                icon = Icons.Filled.Check,
                modifier = Modifier
                    .defaultMinSize(minWidth = minWidth)
                    .height(height)
            )
        }
    }
}


@Composable
fun BotonUser(
navigationController: NavHostController,
idUser:Int,
editable:Boolean,
modifier: Modifier = Modifier,
text: String? = null,
icon: ImageVector? = null,

) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable{
                navigationController.navigate("userDetail/$idUser/$editable")
            }
            .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.5.dp,
                color = MaterialTheme.colors.background,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(6.dp)
    ) {
        if(text != null) {
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
        if(icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}

@Composable
fun PostTabView(
    modifier: Modifier = Modifier,
    imageWithTexts: List<ImageWithText>,
    onTabSelected: (selectedIndex: Int) -> Unit
) {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val inactiveColor = Color(0xFF777777)
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        contentColor = MaterialTheme.colors.primaryVariant,
        modifier = modifier
    ) {
        imageWithTexts.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                selectedContentColor = Color.Black,
                unselectedContentColor = inactiveColor,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)
                }
            ) {
                Icon(
                    painter = item.image,
                    contentDescription = item.text,
                    tint = if(selectedTabIndex == index) Color.Black else inactiveColor,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp)
                )
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MostrarRutas(
    posts: List<Painter>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .scale(1.01f)
    ) {
        items(posts.size) {

        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MostrarRutasLikes(
    posts: List<Painter>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .scale(1.01f)
    ) {
        items(posts.size) {
            Image(
                painter = posts[it],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(
                        width = 1.dp,
                        color = Color.White
                    )
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MostrarSeguidos(
    posts: List<Painter>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .scale(1.01f)
    ) {
        items(posts.size) {
            Image(
                painter = posts[it],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(
                        width = 1.dp,
                        color = Color.White
                    )
            )
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MostrarSeguidores(
    posts: List<Painter>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .scale(1.01f)
    ) {
        items(posts.size) {
            Image(
                painter = posts[it],
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(
                        width = 1.dp,
                        color = Color.White
                    )
            )
        }
    }
}


data class ImageWithText(
    val image: Painter,
    val text: String
)