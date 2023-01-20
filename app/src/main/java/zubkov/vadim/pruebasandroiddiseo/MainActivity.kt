package zubkov.vadim.pruebasandroiddiseo

import android.Manifest
import android.content.pm.PackageManager
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.common.internal.service.Common
import zubkov.vadim.pruebasandroiddiseo.Navigation.CustomNavigator
import zubkov.vadim.pruebasandroiddiseo.Screens.Logins.LoginScreenOne
import zubkov.vadim.pruebasandroiddiseo.Screens.MapScreen.MapaGoogle
import zubkov.vadim.pruebasandroiddiseo.ui.theme.PruebasAndroidDiseñoTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PruebasAndroidDiseñoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CustomNavigator()
                    //MapaGoogle()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PruebasAndroidDiseñoTheme {
        CustomNavigator()
    }
}