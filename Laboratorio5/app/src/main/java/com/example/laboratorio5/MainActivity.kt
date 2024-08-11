package com.example.laboratorio5


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.laboratorio5.ui.theme.Laboratorio5Theme
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.laboratorio5.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio5Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    vistaAplicacion()
                }
            }
        }
    }
}

val FondoColor = Color(0xFFe1f2f8)
val Coloricono = Color(0xFF00b2ca)
val Contornotexto = Color(0xFF828282)
val Colorjornada = Color(0xFF9709D7)
val Coloriniciar = Color(0xFFFD5901)

@Composable
fun vistaAplicacion(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(FondoColor)
                    .height(80.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CircleIcon()
                    Text(text = "Actualización disponible", fontSize = 15.sp)

                    Button(
                        onClick = {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp")
                            )
                            context.startActivity(intent)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Coloricono
                        )
                    ) {
                        Text(
                            text = "Descargar",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                        .wrapContentHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Sábado", fontSize = 35.sp, fontWeight = FontWeight.Bold)
                    Text(text = "6 de julio", fontSize = 20.sp, fontWeight = FontWeight.Normal)
                }
                Column {
                    Spacer(modifier = Modifier.height(28.dp))
                    Box(
                        modifier = Modifier
                            .wrapContentHeight()
                    ) {
                        Box(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Contornotexto,
                                    shape = RoundedCornerShape(6.dp)
                                )
                                .padding(horizontal = 7.dp, vertical = 5.dp)
                                .height(30.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Terminar Jornada",
                                fontWeight = FontWeight.Normal,
                                fontSize = 20.sp,
                                color = Colorjornada
                            )
                        }
                    }
                }
            }

            // Localization

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .shadow(8.dp, shape = RoundedCornerShape(8.dp))
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {

                Row {
                    Text(text = "Don Emiliano", fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(92.dp))
                    IconButton(onClick = {
                        val gmmIntentUri = Uri.parse("geo:0,0?q=14.599187,-90.506687(Don Emiliano)")
                        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                        mapIntent.setPackage("com.google.android.apps.maps")
                        context.startActivity(mapIntent)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.direccion),
                            contentDescription = "direccion",
                            tint = Colorjornada
                        )
                    }

                }
                Text(text = "C.C.Oakland Mall, Diagonal 6 13-01", fontSize = 15.sp)
                Text(text = "10:00AM 10:00PM", color = Contornotexto)

                Row {
                    Button(
                        onClick = {
                            Toast.makeText(
                                context,
                                "Adriana Sophia Palacios Contreras",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Coloriniciar,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .height(43.dp)
                            .width(180.dp)
                            .weight(0.5f)
                    ) {
                        Text(
                            text = "Iniciar",
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    Button(
                        onClick = {
                            Toast.makeText(
                                context,
                                "Tipo de Comida: Española\nCosto: QQ",
                                Toast.LENGTH_LONG
                            ).show()
                        },
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Coloriniciar
                        ),
                        modifier = Modifier
                            .height(43.dp)
                            .width(180.dp)
                            .weight(0.5f)
                    ) {
                        Text(
                            text = "Detalles",
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CircleIcon() {
    Box(
        modifier = Modifier
            .size(45.dp)
            .clip(CircleShape)
            .background(Coloricono)
            .border(2.dp, Color.Transparent, CircleShape)
            .padding(10.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(25.dp)
                .background(Color.Transparent)
                .clip(CircleShape),
            tint = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun vistaAplicacionPreview() {
    Laboratorio5Theme {
        Surface(modifier = Modifier.fillMaxSize()) {
            vistaAplicacion(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
