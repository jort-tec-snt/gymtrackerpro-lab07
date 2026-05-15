package com.semana06.gymtrackerpro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.semana06.gymtrackerpro.data.AppDatabase
import com.semana06.gymtrackerpro.data.Rutina
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarRutinaScreen(
    navController: NavController,
    usuarioId: Int
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    
    val fechaHoy = remember { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date()) }

    var ejercicio by remember { mutableStateOf("") }
    var grupoMuscular by remember { mutableStateOf("") }
    var series by remember { mutableStateOf("") }
    var repeticiones by remember { mutableStateOf("") }
    var pesoKg by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf(fechaHoy) }

    var submitted by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva Rutina", fontWeight = FontWeight.Bold) },
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
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AgregarSectionTitle(icon = Icons.Default.FitnessCenter, title = "Información del Ejercicio")
            
            AgregarCustomTextField(
                value = ejercicio,
                onValueChange = { ejercicio = it },
                label = "Ejercicio",
                icon = Icons.AutoMirrored.Filled.DirectionsRun,
                isError = submitted && ejercicio.isBlank()
            )

            AgregarCustomTextField(
                value = grupoMuscular,
                onValueChange = { grupoMuscular = it },
                label = "Grupo Muscular",
                icon = Icons.Default.AccessibilityNew,
                isError = submitted && grupoMuscular.isBlank()
            )

            Spacer(modifier = Modifier.height(24.dp))
            AgregarSectionTitle(icon = Icons.Default.BarChart, title = "Cargas y Series")

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                AgregarCustomTextField(
                    value = series,
                    onValueChange = { if (it.all { char -> char.isDigit() }) series = it },
                    label = "Series",
                    icon = Icons.Default.Repeat,
                    modifier = Modifier.weight(1f),
                    keyboardType = KeyboardType.Number,
                    isError = submitted && series.isBlank()
                )
                AgregarCustomTextField(
                    value = repeticiones,
                    onValueChange = { if (it.all { char -> char.isDigit() }) repeticiones = it },
                    label = "Reps",
                    icon = Icons.Default.Replay,
                    modifier = Modifier.weight(1f),
                    keyboardType = KeyboardType.Number,
                    isError = submitted && repeticiones.isBlank()
                )
            }

            AgregarCustomTextField(
                value = pesoKg,
                onValueChange = { pesoKg = it },
                label = "Peso (Kg)",
                icon = Icons.Default.Scale,
                keyboardType = KeyboardType.Decimal,
                isError = submitted && pesoKg.isBlank()
            )

            Spacer(modifier = Modifier.height(24.dp))
            AgregarSectionTitle(icon = Icons.Default.Event, title = "Fecha de Entrenamiento")

            AgregarCustomTextField(
                value = fecha,
                onValueChange = { fecha = it },
                label = "Fecha",
                icon = Icons.Default.CalendarToday,
                isError = submitted && fecha.isBlank()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    submitted = true
                    if (ejercicio.isNotBlank() && series.isNotBlank() && repeticiones.isNotBlank()) {
                        scope.launch {
                            val db = AppDatabase.getDatabase(context)
                            val nuevaRutina = Rutina(
                                usuarioId = usuarioId,
                                ejercicio = ejercicio,
                                grupoMuscular = grupoMuscular,
                                series = series.toIntOrNull() ?: 0,
                                repeticiones = repeticiones.toIntOrNull() ?: 0,
                                pesoKg = pesoKg.toDoubleOrNull() ?: 0.0,
                                fecha = fecha
                            )
                            db.rutinaDao().insertarRutina(nuevaRutina)
                            navController.popBackStack()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(Icons.Default.Save, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("GUARDAR RUTINA", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

            TextButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            ) {
                Text("Cancelar", color = Color.Gray)
            }
        }
    }
}

@Composable
fun AgregarSectionTitle(icon: ImageVector, title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun AgregarCustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        leadingIcon = { Icon(icon, contentDescription = null) },
        modifier = modifier.fillMaxWidth().padding(bottom = 12.dp),
        shape = RoundedCornerShape(12.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        isError = isError,
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = Color.LightGray
        )
    )
}
