package zubkov.vadim.pruebasandroiddiseo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import zubkov.vadim.pruebasandroiddiseo.Dto.StaticData
import zubkov.vadim.pruebasandroiddiseo.Model.Usuario

class GlobalViewModel:ViewModel() {

    private val _usuarioRegistrado = MutableLiveData<Usuario?>()
    var usuarioRegistrado: LiveData<Usuario?> = _usuarioRegistrado
    fun loginUsuario(userId: Int) {
        _usuarioRegistrado.value = StaticData().getUsuarios().get(userId)
    }

    fun logout(){

    }
}
