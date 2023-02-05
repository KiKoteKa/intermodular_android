package zubkov.vadim.pruebasandroiddiseo.Dto

import zubkov.vadim.pruebasandroiddiseo.Model.Ruta
import zubkov.vadim.pruebasandroiddiseo.Model.Usuario
import java.util.*

class StaticData {
    val categorias = listOf("Senderismo", "Carrera", "Alpinismo",
        "Cicloturismo","A pie","Escalada","Motociclismo","Esquí de Montaña","Barranco","Pruebas")

    fun getCategoria(id:Int):String
    {
        return categorias[id];
    }

    private val dificultad = listOf("Facil","Media","Dificil")

    fun getDificultad(id:Int):String
    {
        return dificultad[id];
    }


    private val rutas : List<Ruta> = listOf(
        Ruta("1", Date(),"Ruta de Ordesa",1,100.0,0,260,"descrip", Usuario("1","Paco","Jose","","Paco",Date()),1,4.1),
        Ruta("2", Date(),"Ruta de Pepito",2,900.0,1,260,"descrip", Usuario("2","Jaime","Jose","","Jaime",Date()),1,0.1),
        Ruta("3", Date(),"Ruta de Valencia",3,240.0,2,260,"descrip", Usuario("3","Vicente","Jose","","Vicente",Date()),1,1.2),
        Ruta("4", Date(),"Ruta de Castellon",4,30.0,2,260,"descrip", Usuario("1","Paco","Jose","","Paco",Date()),1,3.3),
        Ruta("5", Date(),"Ruta de Ordesa",5,510.0,2,260,"descrip", Usuario("3","Maria","Jose","","Maria",Date()),1,5.0),
        Ruta("6", Date(),"Ruta de Burdeos",6,712.0,1,260,"descrip", Usuario("1","Paco","Jose","","Paco",Date()),1,1.8),
    )

    fun getRutas():List<Ruta>
    {
        return rutas;
    }
}