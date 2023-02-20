package zubkov.vadim.pruebasandroiddiseo.Screens.MapScreen

import android.Manifest
import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import zubkov.vadim.pruebasandroiddiseo.GlobalViewModel
import zubkov.vadim.pruebasandroiddiseo.Screens.Scaffold.BottomBarContent
import zubkov.vadim.pruebasandroiddiseo.Screens.Scaffold.TopBarContent



@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapaGoogle(navigationController: NavHostController,globalViewModel: GlobalViewModel) {
    val locationPermissionState = rememberPermissionState(
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    Scaffold(
        topBar = { TopBarContent() },
        bottomBar = { BottomBarContent(navigationController = navigationController)}
    ) {
        if (locationPermissionState.status.isGranted) {
            Box(
                Modifier
                    .height(608.dp)
            ){
                val uiSettings = remember {
                    MapUiSettings(myLocationButtonEnabled = true)
                }
                val properties by remember {
                    mutableStateOf(MapProperties(isMyLocationEnabled = true))
                }

                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    properties = properties,
                    uiSettings = uiSettings
                ){

                }
                /*Mapa(){

                }*/
            }
        } else {
            LaunchedEffect(!locationPermissionState.status.isGranted){
                locationPermissionState.launchPermissionRequest()
            }
        }
    }
}

@Composable
fun Mapa(onReady:(GoogleMap) -> Unit){
    val context = LocalContext.current
    val mapView = remember{MapView(context)}
    val lifeCycle = LocalLifecycleOwner.current.lifecycle
    lifeCycle.addObserver(RememberMapLifeCircle(map = mapView))
    
    AndroidView(
        factory = {
            mapView.apply {
                mapView.getMapAsync { googleMap ->
                    val nivelZoom = 5f
                    val posicion = LatLng( 40.4165000,-3.7025600)

                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, nivelZoom ))
                    //googleMap.addMarker(MarkerOptions().position(posicion))
                }
            }
        }
    )
}

@Composable
fun RememberMapLifeCircle(map: MapView): LifecycleObserver{
    return remember {
        LifecycleEventObserver{ source, event->
            when(event){
                Lifecycle.Event.ON_CREATE -> map.onCreate(Bundle())
                Lifecycle.Event.ON_START -> map.onStart()
                Lifecycle.Event.ON_RESUME -> map.onResume()
                Lifecycle.Event.ON_PAUSE -> map.onPause()
                Lifecycle.Event.ON_STOP -> map.onStop()
                Lifecycle.Event.ON_DESTROY -> map.onDestroy()
                Lifecycle.Event.ON_ANY -> throw java.lang.IllegalStateException()
            }
        }
    }
}