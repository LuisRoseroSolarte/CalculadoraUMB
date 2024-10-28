package com.example.jekpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp // Importar para usar tamaños de fuente
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import androidx.compose.material3.TextField // Para usar el composable TextField
import androidx.compose.runtime.mutableStateOf // Para crear variables de estado
import androidx.compose.runtime.remember // Para recordar el estado a lo largo de las recomposiciones
import androidx.compose.material3.Button
import android.content.Intent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.*




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mensaje("Calculadora", "Ingrese el primer número", "Ingrese el segundo número")
        }
    }
}

@Composable
fun mensaje(texto: String, numero1: String, numero2: String) {
    val inputNumero1 = remember { mutableStateOf("") }
    val inputNumero2 = remember { mutableStateOf("") }
    val context = LocalContext.current // Obtener contexto para la navegación

    Column ( horizontalAlignment = Alignment.CenterHorizontally, // Centrar horizontalmente todos los elementos
        modifier = Modifier.fillMaxSize() ){
        Text(text = texto, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Image(
            painter = painterResource(R.drawable.calculadora1),
            contentDescription = "Contact profile picture",
            modifier = Modifier.size(400.dp)
        )

        Text(text = numero1, fontSize = 20.sp)
        TextField(
            value = inputNumero1.value,
            onValueChange = { inputNumero1.value = it },
            label = { Text("") }
        )
        Text(text = numero2, fontSize =20.sp )
        TextField(
            value = inputNumero2.value,
            onValueChange = { inputNumero2.value = it },
            label = { Text("") })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val intent = Intent(context, Resultado::class.java).apply {
                putExtra("numero1", inputNumero1.value.toDoubleOrNull() ?: 0.0)
                putExtra("numero2", inputNumero2.value.toDoubleOrNull() ?: 0.0)
            }
            context.startActivity(intent)
        }) {
            Text("Ir a Resultado")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMessageCard() {
    mensaje("Calculadora", "Ingrese el primer número", "Ingrese el segundo número")
}