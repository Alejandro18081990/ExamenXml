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
        for (ingrediente in listado) {
            Log.d("VerIngredientesFicheroSimple", ingrediente.nombre.toString())
            Log.d("VerIngredientesFicheroSimple", ingrediente.toString())
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
            Ingrediente(Alimento(Proteina(5f), Grasa(5f), Hidrato(5f)), "Azucar"),
            Ingrediente(Alimento(Proteina(5f), Grasa(5f), Hidrato(5f)), "Leche")
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