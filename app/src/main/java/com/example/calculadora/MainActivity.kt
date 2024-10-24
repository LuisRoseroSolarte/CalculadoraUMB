package com.example.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculadora.ui.theme.CalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraTheme {
                Proceso()
            }
        }
    }

    @Composable
    private fun Proceso() {
        // Variables de estado para los TextField
        var text1 by remember { mutableStateOf("") }
        var text2 by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Agrega espacio alrededor del contenido
            verticalArrangement = Arrangement.Center, // Centra verticalmente
            horizontalAlignment = Alignment.CenterHorizontally // Centra horizontalmente
        ) {
            // Elementos composables
            Text(text = "CALCULADORA:")
            Image(
                painter = painterResource(R.drawable.calculadora),
                contentDescription = "Calculadora"
            )
            Text(text = "Ingrese el primer número:")
            TextField(
                value = text1,
                onValueChange = { text1 = it },
                label = { Text("Primer número") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            // Segundo TextField
            Text(text = "Ingrese el segundo número:")
            TextField(
                value = text2,
                onValueChange = { text2 = it },
                label = { Text("Segundo número") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewProceso() {
        CalculadoraTheme {
            Proceso()
        }
    }
}



