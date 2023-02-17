package zubkov.vadim.pruebasandroiddiseo.Screens.Rutas.Models

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun Separador(){
    Divider(color = MaterialTheme.colors.secondary, modifier = Modifier
        .fillMaxWidth()
        .padding(0.dp, 10.dp, 0.dp, 0.dp)
        .height(1.dp))
}

@Composable
fun AnimacionCorazon() {

    val interactionSource = MutableInteractionSource()
    val coroutineScope = rememberCoroutineScope()

    var enabled by remember { mutableStateOf(false) }

    val scale = remember { Animatable(1f) }

    Icon(
        imageVector = Icons.Outlined.Favorite,
        contentDescription = "favorito",
        tint = if (enabled) Color.Red else Color.LightGray,
        modifier = Modifier
            .scale(scale = scale.value)
            .size(size = 24.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                enabled = !enabled
                coroutineScope.launch {
                    scale.animateTo(
                        0.8f,
                        animationSpec = tween(100),
                    )
                    scale.animateTo(
                        1f,
                        animationSpec = tween(100),
                    )
                }
            }
    )
}