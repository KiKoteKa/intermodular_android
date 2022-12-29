package zubkov.vadim.pruebasandroiddiseo.Screens.MapScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap

@Composable
fun MapaGoogle(){
    GoogleMap(
        modifier = Modifier
            .fillMaxSize()
    )
}