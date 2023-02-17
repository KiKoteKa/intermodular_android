package zubkov.vadim.pruebasandroiddiseo.Screens.Scaffold

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import zubkov.vadim.pruebasandroiddiseo.Navigation.Routes
import zubkov.vadim.pruebasandroiddiseo.R

@Composable
fun TopBarContent(){
    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Box(
            Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painterResource(R.drawable.senderista),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.padding(5.dp)
                        .weight(1f)
                )
                Text(
                    "SENDERO SUR",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(2.5f).padding(10.dp,0.dp)
                )
            }
        }
    }
}


@Composable
fun CustomBottomBar(navigationController:NavHostController)
{
    BottomAppBar(
        modifier = Modifier
            .height(65.dp)
            .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
        cutoutShape = CircleShape,
        elevation = 22.dp
    ) {
        BottomBarContent(navigationController)
    }
}


@Composable
fun BottomBarContent(navigationController: NavHostController) {
    val navBackStackEntry by navigationController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination
    BottomNavigation(
        modifier = Modifier
            .padding(12.dp, 0.dp, 12.dp, 0.dp)
            .height(100.dp),
        elevation = 0.dp
    ) {

        BottomNavigationItem(
            icon = {Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "home",
                        modifier = Modifier.size(35.dp),
                    )},
            label = {Text(text = "Rutas")},
            selected = currentRoute?.hierarchy?.any { it.route == Routes.Home.route } == true,
            selectedContentColor = Color(R.color.purple_700),
            unselectedContentColor = Color.White.copy(alpha = 0.4f),
            onClick = {navigationController.navigate(Routes.Home.route)  }
        )

        BottomNavigationItem(
            icon = {Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "profile",
                    modifier = Modifier.size(35.dp),
                )},
            label = {Text(text = "Perfil",)},
            selected = currentRoute?.hierarchy?.any { it.route == Routes.Users.route } == true,
            selectedContentColor = Color(R.color.purple_700),
            unselectedContentColor = Color.White.copy(alpha = 0.4f),
            onClick = {navigationController.navigate(Routes.Users.route) }
        )
    }
}
