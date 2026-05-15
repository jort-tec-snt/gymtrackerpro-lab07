package com.semana06.gymtrackerpro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.semana06.gymtrackerpro.data.AppDatabase
import com.semana06.gymtrackerpro.data.Rutina

@Composable
fun ListaRutinasScreen(
    navController: NavController,
    usuarioId: Int
) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    var rutinas by remember { mutableStateOf(listOf<Rutina>()) }

    LaunchedEffect(Unit) {
        rutinas = db.rutinaDao().listarPorUsuario(usuarioId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "Mis Rutinas",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (rutinas.isEmpty()) {
            Box(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text("No tienes rutinas registradas")
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(rutinas) { rutina ->
                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(text = rutina.ejercicio, style = MaterialTheme.typography.titleLarge)
                            Text(text = "Grupo: ${rutina.grupoMuscular}")
                            Text(text = "Series: ${rutina.series} | Reps: ${rutina.repeticiones}")
                            Text(text = "Peso: ${rutina.pesoKg} kg")
                            Text(text = "Fecha: ${rutina.fecha}", style = MaterialTheme.typography.bodySmall)

                            Button(
                                onClick = { navController.navigate("detalle_rutina/${rutina.id}") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.dp)
                            ) {
                                Text("Ver Detalle / Editar")
                            }
                        }
                    }
                }
            }
        }

        Button(
            onClick = { navController.navigate("agregar_rutina/$usuarioId") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Agregar Nueva Rutina")
        }

        TextButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al Menú")
        }
    }
}