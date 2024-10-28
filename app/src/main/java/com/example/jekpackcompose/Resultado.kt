package com.example.jekpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp // Importar para usar tamaños de fuente
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import androidx.compose.material3.Button
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.*



class Resultado : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val numero1 = intent.getDoubleExtra("numero1", 0.0)
        val numero2 = intent.getDoubleExtra("numero2", 0.0)
        setContent {
            Operacion(numero1,numero2)
        }
    }
}

@Composable
fun Operacion(numero1: Double, numero2: Double, modifier: Modifier = Modifier) {
    val context = LocalContext.current // Obtener contexto para la navegación
    Column( horizontalAlignment = Alignment.CenterHorizontally, // Centrar horizontalmente todos los elementos
        verticalArrangement = Arrangement.SpaceEvenly, // Espacio uniforme entre elementos
        modifier = Modifier.fillMaxSize().padding(16.dp) // Añadir padding alrededor
    ) {
        Spacer(modifier = Modifier.height(25.dp))
        Text(text = "Resultados",fontSize =20.sp,fontWeight = FontWeight.Bold)

        val suma = numero1 + numero2
        val resta = numero1 - numero2
        val multiplicacion = numero1 * numero2
        val division = if (numero2 != 0.0) String.format("%.1f", numero1 / numero2) else "Error: División por cero"

        Text(text = "La suma es: $suma", fontSize = 20.sp)
        Text(text = "La resta es: $resta", fontSize = 20.sp)
        Text(text = "La multiplicación es: $multiplicacion", fontSize = 20.sp)
        Text(text = "La división es: $division", fontSize = 20.sp)



        Button(onClick = {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }) {
            Text(" Regresar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOperacion() {
    Operacion(5.0,3.0 )
}