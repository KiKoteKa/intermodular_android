package zubkov.vadim.pruebasandroiddiseo.Screens.Rutas.Components

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import zubkov.vadim.pruebasandroiddiseo.Dto.StaticData
import zubkov.vadim.pruebasandroiddiseo.Model.Ruta

class CardExtendedViewModel:ViewModel() {
    /*
    private val _showConfirmarBorrado = MutableLiveData(true)
    val showConfirmarBorrado: LiveData<Boolean> = _showConfirmarBorrado

    fun openConfirmarBorrado() {
        _showConfirmarBorrado.value = true
    }

    fun confirmarBorrado(idRuta:Int,navigationController:NavHostController) {
        _showConfirmarBorrado.value = false
        StaticData().deleteRuta(idRuta)
        navigationController.navigate("home")
    }

    fun cerrarConfirmarBorrado() {
        _showConfirmarBorrado.value = false
    }

     */
}
