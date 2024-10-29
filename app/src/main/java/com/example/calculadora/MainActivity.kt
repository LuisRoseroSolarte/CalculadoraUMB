package com.example.calculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculadora.ui.theme.CalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculadoraTheme {
                CalculadoraUI()
            }
        }
    }

    @Composable
    fun CalculadoraUI() {
        // Variables de estado
        var text1 by remember { mutableStateOf("") }
        var text2 by remember { mutableStateOf("") }
        var resultado by remember { mutableStateOf("") }
        var operacionSeleccionada by remember { mutableStateOf("+") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "CALCULADORA", style = MaterialTheme.typography.headlineMedium)
            Image(
                painter = painterResource(R.drawable.calculadora),
                contentDescription = "Calculadora"
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Primer número
            TextField(
                value = text1,
                onValueChange = { text1 = it },
                label = { Text("Primer número") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Segundo número
            TextField(
                value = text2,
                onValueChange = { text2 = it },
                label = { Text("Segundo número") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Botones de operaciones
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                OperacionButton("+", operacionSeleccionada) { operacionSeleccionada = it }
                OperacionButton("-", operacionSeleccionada) { operacionSeleccionada = it }
                OperacionButton("*", operacionSeleccionada) { operacionSeleccionada = it }
                OperacionButton("/", operacionSeleccionada) { operacionSeleccionada = it }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Botón de calcular
            Button(
                onClick = {
                    resultado = calcularResultado(text1, text2, operacionSeleccionada)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calcular")
            }

            // Mostrar resultado
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Resultado: $resultado", style = MaterialTheme.typography.headlineSmall)
        }
    }

    @Composable
    fun OperacionButton(
        simbolo: String,
        seleccionActual: String,
        onSeleccion: (String) -> Unit
    ) {
        Button(
            onClick = { onSeleccion(simbolo) },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (simbolo == seleccionActual) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(simbolo)
        }
    }

    // Función para calcular el resultado
    private fun calcularResultado(text1: String, text2: String, operacion: String): String {
        val numero1 = text1.toDoubleOrNull()
        val numero2 = text2.toDoubleOrNull()

        if (numero1 == null || numero2 == null) {
            return "Entrada no válida"
        }

        return when (operacion) {
            "+" -> (numero1 + numero2).toString()
            "-" -> (numero1 - numero2).toString()
            "*" -> (numero1 * numero2).toString()
            "/" -> if (numero2 != 0.0) (numero1 / numero2).toString() else "División por 0"
            else -> "Operación no válida"
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewCalculadoraUI() {
        CalculadoraTheme {
            CalculadoraUI()
        }
    }
}
