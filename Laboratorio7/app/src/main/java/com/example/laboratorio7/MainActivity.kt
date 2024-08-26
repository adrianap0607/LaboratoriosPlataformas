package  com.example.laboratorio7

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.laboratorio7.Lab7.NotificationCenter
import androidx.compose.material3.MaterialTheme
import com.example.laboratorio7.ui.theme.Laboratorio7Theme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio7Theme  {
                NotificationCenter() // Asegúrate de que esta función esté correctamente importada
            }
        }
    }
}
