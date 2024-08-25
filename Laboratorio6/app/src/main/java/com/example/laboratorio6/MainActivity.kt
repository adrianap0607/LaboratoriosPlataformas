package com.example.laboratorio6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.laboratorio6.ejercicio6.CounterApp
import com.example.laboratorio6.ui.theme.Laboratorio6Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio6Theme {
                // Asegúrate de que la superficie tenga el color de fondo correcto
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    CounterApp() // Llama a tu Composable principal
                }
            }
        }
    }
}

// Composable para previsualizar la aplicación en Android Studio
@Preview(showBackground = true)
@Composable
fun VistaAplicacion() {
    Laboratorio6Theme {
        CounterApp()
    }
}
