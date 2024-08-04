package com.example.laboratorio4.Portada

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.laboratorio4.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio4.ui.theme.Laboratorio4Theme

val colorverde = Color(0xFF008259)

@Composable
fun portada(modifier: Modifier = Modifier){

    Box(modifier = Modifier.border(7.dp, colorverde))
    Image(painter = painterResource(id = R.drawable.uvg) , contentDescription ="Logo",modifier.graphicsLayer(alpha = 0.25f))

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 150.dp)
        ) {
            Text(
                text = "Universidad del Valle de Guatemala",
                color = Color.Black,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 45.sp,
                modifier = Modifier.padding(start = 25.dp, end = 25.dp, top = 75.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Text(
                text = "Programación de plataformas móviles, Sección 30",
                color = Color.Black,
                fontSize = 26.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                lineHeight = 37.sp,
                modifier = Modifier.padding(start = 5.dp, end = 5.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "INTEGRANTES",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(0.5f)

                )
            Text(
                text = "Adriana Palacios\n Bryan Martinez",
                fontSize = 21.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.weight(0.5f)
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = "CATEDRÁTICO",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(0.5f)

            )
            Text(
                text = "Juan Carlos Durini",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .weight(0.5f)
                    .padding(end = 20.dp)
            )

        }
        Text(
            text = "Adriana Palacios\n23044",
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .weight(0.5f)
                .padding(end = 20.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}




@Preview(showBackground = true)
@Composable
private fun PreviewEjercicio(){
    Laboratorio4Theme{
        Surface(modifier = Modifier.fillMaxSize()){
            portada(modifier = Modifier.fillMaxSize())
        }

      }
    }
