package com.example.malejandronunezrodriguezexamenxml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.malejandronunezrodriguezexamenxml.modelo.entidades.*
import com.example.malejandronunezrodriguezexamenxml.modelo.modelView.ViewModel

class MainActivity : AppCompatActivity() {

    lateinit var modelViewReceta: ViewModel
    var listado: MutableList<Ingrediente> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        modelViewReceta = ViewModelProvider(this)[ViewModel::class.java]


        //Lectura fichero
        listado = modelViewReceta.procesarFicheroXml()
        for (alumno in listado){
            Log.d("VerIngredientesFicheroSimple", alumno.nombre.toString())
            Log.d("VerIngredientesFicheroSimple", alumno.toString())
        }

        Log.d(
            "VerIngredientesSeparador",
            "---------------------------------------------------------------"
        )

        //Lectura fichero mediante SAX
        listado = modelViewReceta.procesarArchivoXMLSAX()
        for (ingrediente in listado) {
            Log.d("VerIngredientesSAX", ingrediente.nombre)
            Log.d("VerIngredientesSAX", ingrediente.toString())
        }
        Log.d(
            "VerIngredientesSeparador",
            "---------------------------------------------------------------"
        )

        //Escritura en fichero interno
        var receta = Receta()
        var ingredientes = arrayOf(
            Ingrediente(Alimento(Proteina(5), Grasa(5), Hidrato(5)), "Azucar"),
            Ingrediente(Alimento(Proteina(5), Grasa(5), Hidrato(5)), "Leche")
        )
        for (ingrediente in ingredientes)
            modelViewReceta.addIngrediente(ingrediente)
        listado = modelViewReceta.procesarFicheroXmlInterno()
        for (ingrediente in listado) {
            Log.d("VerIngredientesInterno", ingrediente.nombre)
            Log.d("VerIngredientesInterno", ingrediente.toString())
        }

    }
}