package com.example.laboratorio7.Lab7

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Date
import java.text.SimpleDateFormat
import java.util.Locale
import com.example.laboratorio7.ui.theme.Laboratorio7Theme

// Enum para representar los tipos de notificación
enum class NotificationType {
    GENERAL,
    NEW_POST,
    NEW_MESSAGE,
    NEW_LIKE
}


data class Notification(
    val id: Int,
    val title: String,
    val body: String,
    val sendAt: Date,
    val type: NotificationType
)


@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.toDate(): Date {
    return Date.from(this.atZone(java.time.ZoneId.systemDefault()).toInstant())
}

// Función para generar notificaciones falsas
@RequiresApi(Build.VERSION_CODES.O)
fun generateFakeNotifications(): List<Notification> {
    val notifications = mutableListOf<Notification>()
    val titles = listOf(
        "Nueva versión disponible",
        "Nuevo post de Juan",
        "Mensaje de Maria",
        "Te ha gustado una publicación"
    )
    val bodies = listOf(
        "La aplicación ha sido actualizada a v1.0.2. Ve a la PlayStore y actualízala!",
        "Te han etiquetado en un nuevo post. ¡Míralo ahora!",
        "No te olvides de asistir a esta capacitación mañana, a las 6pm, en el Intecap.",
        "A Juan le ha gustado tu publicación. ¡Revisa tu perfil!"
    )
    val types = NotificationType.entries.toTypedArray()

    val currentDate = LocalDate.now()
    for (i in 1..50) {
        val daysAgo = (0..10).random()
        val hoursAgo = (0..23).random()
        val minutesAgo = (0..59).random()
        val sendAt = LocalDateTime.of(currentDate.minusDays(daysAgo.toLong()), LocalTime.of(hoursAgo, minutesAgo)).toDate()
        notifications.add(
            Notification(
                id = i,
                title = titles.random(),
                body = bodies.random(),
                sendAt = sendAt,
                type = types.random()
            )
        )
    }
    return notifications
}

// Composable principal que representa el centro de notificaciones
@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationCenter() {
    var selectedFilter by remember { mutableStateOf<NotificationType?>(null) }
    val notifications = remember { generateFakeNotifications() }

    val filteredNotifications = selectedFilter?.let { filter ->
        notifications.filter { it.type == filter }
    } ?: notifications

    Column(modifier = Modifier.fillMaxSize()) {

        // TopAppBar
        TopAppBar(
            title = { Text("Notificaciones") },
            navigationIcon = {
                IconButton(onClick = { /* Acción al presionar el ícono */ }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = "Tipos de notificaciones",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp, start = 16.dp)
        )

        // LazyRow para los chips
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(NotificationType.values()) { type ->
                NotificationFilterChip(
                    text = when (type) {
                        NotificationType.GENERAL -> "General"
                        NotificationType.NEW_POST -> "Nueva Publicación"
                        NotificationType.NEW_MESSAGE -> "Mensajes"
                        NotificationType.NEW_LIKE -> "Likes"
                    },
                    isSelected = selectedFilter == type,
                    onClick = {
                        selectedFilter = if (selectedFilter == type) null else type
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(filteredNotifications) { notification ->
                NotificationItem(notification)
            }
        }
    }
}

// Composable para representar un Chip de filtro
@Composable
fun NotificationFilterChip(text: String, isSelected: Boolean, onClick: () -> Unit) {

    val backgroundColor = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.surface
    val textColor = if (isSelected) MaterialTheme.colorScheme.onSecondary else MaterialTheme.colorScheme.onSurface

    AssistChip(
        onClick = onClick,
        label = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        tint = textColor,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
                Text(text = text, color = textColor)
            }
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = backgroundColor,
            labelColor = textColor
        ),
        modifier = Modifier.height(40.dp)
    )
}

// Composable para representar una notificación individual
@Composable
fun NotificationItem(notification: Notification) {
    val backgroundColor = when (notification.type) {
        NotificationType.GENERAL -> MaterialTheme.colorScheme.primaryContainer
        NotificationType.NEW_POST -> MaterialTheme.colorScheme.secondaryContainer
        NotificationType.NEW_MESSAGE -> MaterialTheme.colorScheme.tertiaryContainer
        NotificationType.NEW_LIKE -> MaterialTheme.colorScheme.primary
    }
    val icon = when (notification.type) {
        NotificationType.GENERAL -> Icons.Default.Notifications
        NotificationType.NEW_POST -> Icons.Default.Add
        NotificationType.NEW_MESSAGE -> Icons.Default.MailOutline
        NotificationType.NEW_LIKE -> Icons.Default.Favorite
    }
    val textColor = MaterialTheme.colorScheme.onPrimaryContainer


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { /* Acción al hacer clic */ },
        colors = CardDefaults.outlinedCardColors(containerColor = Color.Transparent)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(backgroundColor),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(notification.title, fontSize = 16.sp, color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold)


                    val formattedDate = SimpleDateFormat("dd MMM • h:mm a", Locale.getDefault()).format(notification.sendAt)
                    Text(formattedDate, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(notification.body, fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun Laboratorio7Theme() {
    MaterialTheme {
        NotificationCenter()
    }
}
