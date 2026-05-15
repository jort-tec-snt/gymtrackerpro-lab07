package com.semana06.gymtrackerpro.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.semana06.gymtrackerpro.data.AppDatabase
import com.semana06.gymtrackerpro.data.Usuario
import com.semana06.gymtrackerpro.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilUsuarioScreen(
    navController: NavController,
    usuarioId: Int
) {
    val context = LocalContext.current
    val db = AppDatabase.getDatabase(context)
    val scrollState = rememberScrollState()

    var usuario by remember { mutableStateOf<Usuario?>(null) }
    var totalRutinas by remember { mutableIntStateOf(0) }
    var volumenTotal by remember { mutableDoubleStateOf(0.0) }

    LaunchedEffect(usuarioId) {
        usuario = db.usuarioDao().buscarPorId(usuarioId)
        totalRutinas = db.rutinaDao().contarRutinas(usuarioId)
        volumenTotal = db.rutinaDao().calcularPesoTotal(usuarioId) ?: 0.0
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("MI PERFIL", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(scrollState)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Avatar con Iniciales
            val iniciales = usuario?.nombreCompleto?.split(" ")?.take(2)?.mapNotNull { it.firstOrNull() }?.joinToString("")?.uppercase() ?: "?"
            
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = iniciales,
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = usuario?.nombreCompleto ?: "Cargando...",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = usuario?.email ?: "",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Estadísticas
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                StatCard(
                    modifier = Modifier.weight(1f),
                    title = "Rutinas",
                    value = totalRutinas.toString(),
                    icon = Icons.Default.History,
                    containerColor = Color(0xFFE3F2FD),
                    contentColor = Color(0xFF1976D2)
                )
                StatCard(
                    modifier = Modifier.weight(1f),
                    title = "Volumen (kg)",
                    value = volumenTotal.toInt().toString(),
                    icon = Icons.Default.FitnessCenter,
                    containerColor = Color(0xFFE8F5E9),
                    contentColor = Color(0xFF388E3C)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Información adicional
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    ProfileInfoRow(label = "Usuario", value = usuario?.nombreUsuario ?: "")
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 0.5.dp)
                    ProfileInfoRow(label = "Edad", value = "${usuario?.edad ?: ""} años")
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp), thickness = 0.5.dp)
                    ProfileInfoRow(label = "Miembro desde", value = usuario?.fechaRegistro ?: "")
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.LOGIN) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)),
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.AutoMirrored.Filled.Logout, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("CERRAR SESIÓN", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun StatCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    icon: ImageVector,
    containerColor: Color,
    contentColor: Color
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = null, tint = contentColor, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = contentColor)
            Text(text = title, fontSize = 12.sp, color = contentColor.copy(alpha = 0.8f))
        }
    }
}

@Composable
fun ProfileInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontWeight = FontWeight.Medium, color = Color.Gray)
        Text(text = value, fontWeight = FontWeight.Bold)
    }
}
