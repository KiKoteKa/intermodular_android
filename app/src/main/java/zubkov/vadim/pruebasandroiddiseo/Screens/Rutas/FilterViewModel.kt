package zubkov.vadim.pruebasandroiddiseo.Screens.Rutas

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import zubkov.vadim.pruebasandroiddiseo.Model.Ruta

class FilterViewModel:ViewModel() {

    private val _text = MutableLiveData<TextFieldValue>()
    var text: LiveData<TextFieldValue> = _text
    fun updateText(text: TextFieldValue) {
        _text.value = text
    }

    private val _switchBusqueda = MutableLiveData("Ruta")
    var switchBusqueda: LiveData<String> = _switchBusqueda
    fun updateSwitch(value:String) {
        _switchBusqueda.value = value
    }

    private val _listado = MutableLiveData<List<Ruta>>()
    var listado: LiveData<List<Ruta>> = _listado
    fun updateListado(value:List<Ruta>) {
        _listado.value = value
        _countListado.value = value.size;
    }

    private val _countListado = MutableLiveData<Int>()
    var countListado: LiveData<Int> = _countListado


    private val _filtroDificultad = MutableLiveData<ClosedFloatingPointRange<Float>>()
    var filtroDificultad: LiveData<ClosedFloatingPointRange<Float>> = _filtroDificultad
    fun updateFiltroDificultad(value:ClosedFloatingPointRange<Float>) {
        _filtroDificultad.value = value
    }

    private val _filtroDistancia = MutableLiveData<ClosedFloatingPointRange<Float>>()
    var filtroDistancia: LiveData<ClosedFloatingPointRange<Float>> = _filtroDistancia
    fun updateFiltroDistancia(value:ClosedFloatingPointRange<Float>) {
        _filtroDistancia.value = value
    }

    private val _filtroActividades = MutableLiveData<MutableList<String>>()
    var filtroActividades: LiveData<MutableList<String>> = _filtroActividades
    fun updateFiltroActividades(value:MutableList<String>) {
        _filtroActividades.value = value
    }


    fun addActivity(activity:String){
        _filtroActividades.value!!.remove(activity)
        _filtroActividades.value!!.add(activity)
    }
    fun removeActivity(activity:String){
        _filtroActividades.value!!.remove(activity)
    }

    fun activeActivity(activity:String):Boolean{
        return _filtroActividades.value!!.contains(activity)
    }

}
