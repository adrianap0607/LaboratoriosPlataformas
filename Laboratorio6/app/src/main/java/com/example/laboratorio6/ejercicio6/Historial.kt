package com.example.laboratorio6.ejercicio6

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val Colormenosmas = Color(0xFF5B61A1)
val Colorverde = Color(0xFF08A045)

@Composable
fun CounterApp() {
    var contador by rememberSaveable { mutableStateOf(0) }
    var incrementos by rememberSaveable { mutableStateOf(0) }
    var decrementos by rememberSaveable { mutableStateOf(0) }
    var valorMaximo by rememberSaveable { mutableStateOf(0) }
    var cambios by rememberSaveable { mutableStateOf(0) }
    val historial = remember { mutableStateListOf<Pair<Int, Boolean>>() }

    val valorMinimo = if (historial.isNotEmpty()) historial.minOf { it.first } else 0

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 64.dp) // Espacio para el botón de reiniciar
        ) {
            SeccionTitulo(titulo = "Adriana Palacios")
            Spacer(modifier = Modifier.height(16.dp))

            SeccionContador(
                contador = contador,
                onIncrementar = {
                    contador++
                    incrementos++
                    cambios++
                    if (contador > valorMaximo) valorMaximo = contador
                    historial.add(Pair(contador, true))
                },
                onDecrementar = {
                    contador--
                    decrementos++
                    cambios++
                    historial.add(Pair(contador, false))
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            SeccionEstadisticas(
                incrementos = incrementos,
                decrementos = decrementos,
                valorMaximo = valorMaximo,
                valorMinimo = valorMinimo,
                cambios = cambios
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Texto "Historial" fijo
            Text(
                text = "Historial:",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Habilitar scroll solo en los cuadros del historial
            Box(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 16.dp) // Espacio adicional para evitar superposición
            ) {
                SeccionHistorial(historial = historial)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            BotonReiniciar(onReiniciar = {
                contador = 0
                incrementos = 0
                decrementos = 0
                valorMaximo = 0
                cambios = 0
                historial.clear()
            })
        }
    }
}

@Composable
fun SeccionTitulo(titulo: String) {
    Text(
        text = titulo,
        fontSize = 36.sp, // Reducir el tamaño de la fuente
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun SeccionContador(contador: Int, onIncrementar: () -> Unit, onDecrementar: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FloatingActionButton(
            onClick = onDecrementar,
            containerColor = Colormenosmas,
            contentColor = Color.White,
            modifier = Modifier.size(56.dp),
            shape = CircleShape
        ) {
            Text(text = "-", fontSize = 50.sp)
        }
        Text(text = "$contador", fontSize = 48.sp)
        FloatingActionButton(
            onClick = onIncrementar,
            containerColor = Colormenosmas,
            contentColor = Color.White,
            modifier = Modifier.size(56.dp),
            shape = CircleShape
        ) {
            Text(text = "+", fontSize = 35.sp)
        }
    }
}

@Composable
fun SeccionEstadisticas(
    incrementos: Int, decrementos: Int,
    valorMaximo: Int, valorMinimo: Int, cambios: Int
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total incrementos:", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text("$incrementos", fontSize = 28.sp)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total decrementos:", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text("$decrementos", fontSize = 28.sp)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Valor máximo:", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text("$valorMaximo", fontSize = 28.sp)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Valor mínimo:", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text("$valorMinimo", fontSize = 28.sp)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Total cambios:", fontSize = 28.sp, fontWeight = FontWeight.Bold)
            Text("$cambios", fontSize = 28.sp)
        }
    }
}

@Composable
fun SeccionHistorial(historial: List<Pair<Int, Boolean>>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        historial.chunked(5).forEach { filaItems ->
            Row(modifier = Modifier.fillMaxWidth()) {
                filaItems.forEach { (valor, fueIncremento) ->
                    val backgroundColor = if (fueIncremento) Colorverde else Color.Red
                    Text(
                        text = "$valor",
                        fontSize = 18.sp,
                        color = Color.White,
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                            .background(
                                color = backgroundColor,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun BotonReiniciar(onReiniciar: () -> Unit) {
    Button(
        onClick = onReiniciar,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Colormenosmas)
    ) {
        Text("Reiniciar", color = Color.White, fontSize = 22.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun CounterAppPreview() {
    CounterApp()
}
