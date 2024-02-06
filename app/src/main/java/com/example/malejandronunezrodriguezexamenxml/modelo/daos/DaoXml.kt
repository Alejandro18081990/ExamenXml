package com.example.malejandronunezrodriguezexamenxml.modelo.daos

import android.content.Context
import android.content.Context.MODE_APPEND
import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.example.malejandronunezrodriguezexamenxml.modelo.entidades.Ingrediente
import com.example.malejandronunezrodriguezexamenxml.modelo.entidades.Receta
import com.example.malejandronunezrodriguezexamenxml.modelo.handlers.RecetaHandlerXml
import com.example.malejandronunezrodriguezexamenxml.modelo.interfaces.InterfaceDaoReceta
import org.simpleframework.xml.core.Persister
import java.io.*
import javax.xml.parsers.SAXParserFactory

class DaoXml constructor(var context: Context) : InterfaceDaoReceta {

    //Serializar y deserializar
    val serializer = Persister()
    val nombreFichero = "recetas.xml"
    var listaAlumnosAssets = mutableListOf<Ingrediente>()
    var listaIngredientesHandler: MutableList<Ingrediente> = mutableListOf()
    var listaIngredientesFicheroInterno: MutableList<Ingrediente> = mutableListOf()

    override fun addIngrediente(ingrediente: Ingrediente) {
        try {
            listaIngredientesFicheroInterno.add(ingrediente)
            val listReceta = Receta(listaIngredientesFicheroInterno)
            val outputStream = context.openFileOutput(nombreFichero, MODE_APPEND)
            //Pasamos por parámetros lo que escribimos y el dónde lo escribimos
            serializer.write(listReceta, outputStream)
        } catch (e: IOException) {
            Log.d("ErrorAnadir", e.message.toString())
        }
    }


    override fun procesarArchivoXMLSAX(): MutableList<Ingrediente> {
        try {
            val factory = SAXParserFactory.newInstance()
            val parser = factory.newSAXParser()
            val handler = RecetaHandlerXml()
            val inputStream = context.assets.open(nombreFichero)
            parser.parse(inputStream, handler)
            listaIngredientesHandler = handler.listaIngredientes
        } catch (e: Exception) {
            Log.d("ErrorSAX", e.message.toString())
        }
        return listaIngredientesHandler
    }

    override fun procesarFicheroXml(): MutableList<Ingrediente> {
        var inputStream: InputStream? = null
        var reader: InputStreamReader? = null
        try {
            inputStream = context.assets.open(nombreFichero)
            reader = InputStreamReader(inputStream)
            val escuelaListType = serializer.read(Receta::class.java, reader, false)
            listaAlumnosAssets.addAll(escuelaListType.ingredientes)
        } catch (e: Exception) {
            // Manejo de errores
            Log.d("ErrorException0", e.message.toString())
        } finally {
            // Cerrar inputStream y reader
            try {
                reader?.close()
                inputStream?.close()
            } catch (e: IOException) {
                Log.d("ErrorException1", e.message.toString())
            }
        }
        return listaAlumnosAssets
    }

    override fun procesarFicheroXmlInterno(): MutableList<Ingrediente> {
        try {
            val file = File(context.filesDir, nombreFichero)
            val inputStream = FileInputStream(file)
            //Convertimos los datos del fichero interno en objetos de Kotlin
            //desserializandolos
            val escuelaList = serializer.read(Receta::class.java, inputStream)
            Log.d("EscuelaList", escuelaList.toString())
            listaIngredientesFicheroInterno.addAll(escuelaList.ingredientes)
            inputStream.close()
        } catch (e: Exception) {
            Log.d("ErrorInterno", e.message.toString())
        }
        return listaIngredientesFicheroInterno
    }

    override fun copiarArchivoDesdeAsset() {
        val archivoEnAssets = context.assets.open(nombreFichero)
        val archivoInterno = context.openFileOutput(nombreFichero, MODE_PRIVATE)
        archivoEnAssets.copyTo(archivoInterno)
        archivoEnAssets.close()
        archivoInterno.close()
    }

}
