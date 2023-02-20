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

    fun getUsuario(id:Int):Usuario
    {
        return usuarios.filter{it.id == id}.get(0);
    }

    fun getDificultad(id:Int):String
    {
        return dificultad[id];
    }

    fun getCategoria(id:Int):String
    {
        return categorias[id];
    }

    fun deleteRuta(idRuta: Int) {
        rutas = rutas.filter{it.id != idRuta}
    }

    fun getRuta(idRuta: Int): Ruta {
        return rutas.filter{it.id == idRuta}.get(0)
    }

    val categorias = listOf("Senderismo", "Carrera", "Alpinismo",
        "Cicloturismo","A pie","Escalada","Motociclismo","Esquí de Montaña","Barranco","Pruebas")

    private val dificultad = listOf("Facil","Moderado","Dificil","Muy Dificil", "Solo Expertos")

    private val usuarios : List<Usuario> = listOf(
        Usuario(0,"Nerea","Suarez","Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.","guzman.izan@reina.net","nererez", Date(),listOf(1),listOf(2,3,4),listOf(2,3),listOf(2,3,4)),
        Usuario(1,"Ángela","Cortes","Lorem Ipsum is simply dummy text","sola.jordi@quintero.com","angeltes", Date(),listOf(2),listOf(0,4,5),listOf(3,4),listOf(2)),
        Usuario(2,"Teresa","Martínez","Lorem Ipsum is simply dummy text","jose.villa@hispavista.com","teresanez",Date(),listOf(0,3,5),listOf(1),listOf(0,1),listOf(0,3,4)),
        Usuario(3,"Isabel","Velasco","Lorem Ipsum is simply dummy text","xsaenz@yahoo.es","isabelsco",Date(),listOf(4),listOf(1,2),listOf(0,2),listOf(0,1)),
        Usuario(4,"Hector","Marin","Lorem Ipsum is simply dummy text","velazquez.fatima@valenzuela.com","hectoin",Date(),listOf(),listOf(0,1,3),listOf(0,2),listOf(1)),
    )

    private var rutas : List<Ruta> = listOf(
          Ruta(0, Date(),"Ruta de Ordesa",1,100.0,0,260,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", usuarios.get(2)
            ,listOf(R.drawable.paisaje1,R.drawable.background),4.1),
         Ruta(1, Date(),"Ruta de Pepito",2,900.0,4,260,"descrip",getUsuario(0),listOf(R.drawable.background,R.drawable.paisaje2),3.3),
         Ruta(2, Date(),"Ruta de Valencia",3,240.0,2,260,"descrip", getUsuario(1),listOf(R.drawable.paisaje1,R.drawable.paisaje2,R.drawable.paisaje3),1.2),
         Ruta(3, Date(),"Ruta de Castellon",4,30.0,3,260,"descrip",getUsuario(2) ,listOf(R.drawable.paisaje2,R.drawable.paisaje1,R.drawable.background),3.3),
        Ruta(4, Date(),"Ruta de Costera",5,510.0,2,260,"descrip",getUsuario(3),listOf(R.drawable.paisaje3,R.drawable.paisaje2,R.drawable.paisaje1),5.0),
        Ruta(5, Date(),"Ruta de Burdeos",7,712.0,1,260,"descrip", getUsuario(2),listOf(R.drawable.background,R.drawable.paisaje3),1.8),
    )


}