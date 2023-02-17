package zubkov.vadim.pruebasandroiddiseo.Dto

import zubkov.vadim.pruebasandroiddiseo.Model.Ruta
import zubkov.vadim.pruebasandroiddiseo.Model.Usuario
import zubkov.vadim.pruebasandroiddiseo.R
import java.util.*

class StaticData {

    fun getRutas():List<Ruta>
    {
        return rutas;
    }

    fun getUsuarios():List<Usuario>
    {
        return usuarios;
    }

    fun getDificultad(id:Int):String
    {
        return dificultad[id];
    }

    fun getCategoria(id:Int):String
    {
        return categorias[id];
    }

    val categorias = listOf("Senderismo", "Carrera", "Alpinismo",
        "Cicloturismo","A pie","Escalada","Motociclismo","Esquí de Montaña","Barranco","Pruebas")

    private val dificultad = listOf("Facil","Moderado","Dificil","Muy Dificil", "Solo Expertos")

    private val usuarios : List<Usuario> = listOf(
        Usuario(0,"Nerea","Suarez","guzman.izan@reina.net","nererez", Date()),
        Usuario(1,"Ángela","Cortes","sola.jordi@quintero.com","angeltes", Date()),
        Usuario(2,"Teresa","Martínez","jose.villa@hispavista.com","teresanez",Date()),
        Usuario(3,"Isabel","Velasco","xsaenz@yahoo.es","isabelsco",Date()),
        Usuario(4,"Hector","Marin","velazquez.fatima@valenzuela.com","hectoin",Date()),
    )

    private val rutas : List<Ruta> = listOf(
          Ruta(0, Date(),"Ruta de Ordesa",1,100.0,0,260,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", usuarios.get(2)
            ,listOf(R.drawable.background,R.drawable.background),4.1),
         Ruta(1, Date(),"Ruta de Pepito",2,900.0,4,260,"descrip",usuarios.get(0),listOf(R.drawable.background,R.drawable.paisaje2),3.3),
         Ruta(2, Date(),"Ruta de Valencia",3,240.0,2,260,"descrip", usuarios.get(1),listOf(R.drawable.paisaje1,R.drawable.paisaje2,R.drawable.paisaje3),1.2),
         Ruta(3, Date(),"Ruta de Castellon",4,30.0,3,260,"descrip",usuarios.get(2) ,listOf(R.drawable.paisaje2,R.drawable.paisaje1,R.drawable.background),3.3),
        Ruta(4, Date(),"Ruta de Ordesa",5,510.0,2,260,"descrip",usuarios.get(3),listOf(R.drawable.paisaje3,R.drawable.paisaje2,R.drawable.paisaje1),5.0),
        Ruta(5, Date(),"Ruta de Burdeos",7,712.0,1,260,"descrip", usuarios.get(2),listOf(R.drawable.background,R.drawable.paisaje3),1.8),
    )


}