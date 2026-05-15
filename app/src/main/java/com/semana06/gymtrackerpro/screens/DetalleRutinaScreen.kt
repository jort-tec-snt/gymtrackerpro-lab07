package com.semana06.gymtrackerpro.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.semana06.gymtrackerpro.data.AppDatabase
import kotlinx.coroutines.launch

@Composable
fun DetalleRutinaScreen(
    navController: NavController,
    rutinaId: Int
) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val scope = rememberCoroutineScope()

    var ejercicio by remember { mutableStateOf("") }
    var grupoMuscular by remember { mutableStateOf("") }
    var series by remember { mutableStateOf("") }
    var repeticiones by remember { mutableStateOf("") }
    var pesoKg by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var usuarioId by remember { mutableIntStateOf(0) }

    LaunchedEffect(rutinaId) {
        val rutina = db.rutinaDao().buscarPorId(rutinaId)
        rutina?.let {
            ejercicio = it.ejercicio
            grupoMuscular = it.grupoMuscular
            series = it.series.toString()
            repeticiones = it.repeticiones.toString()
            pesoKg = it.pesoKg.toString()
            fecha = it.fecha
            usuarioId = it.usuarioId
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Editar Rutina", style = MaterialTheme.typography.headlineSmall)

                OutlinedTextField(
                    value = ejercicio,
                    onValueChange = { ejercicio = it },
                    label = { Text("Ejercicio") },
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
                )

                OutlinedTextField(
                    value = grupoMuscular,
                    onValueChange = { grupoMuscular = it },
                    label = { Text("Grupo muscular") },
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
                )

                OutlinedTextField(
                    value = series,
                    onValueChange = { series = it },
                    label = { Text("Series") },
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
                )

                OutlinedTextField(
                    value = repeticiones,
                    onValueChange = { repeticiones = it },
                    label = { Text("Repeticiones") },
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
                )

                OutlinedTextField(
                    value = pesoKg,
                    onValueChange = { pesoKg = it },
                    label = { Text("Peso Kg") },
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
                )

                Button(
                    onClick = {
                        scope.launch {
                            val rutinaActualizada = db.rutinaDao().buscarPorId(rutinaId)?.copy(
                                ejercicio = ejercicio,
                                grupoMuscular = grupoMuscular,
                                series = series.toIntOrNull() ?: 0,
                                repeticiones = repeticiones.toIntOrNull() ?: 0,
                                pesoKg = pesoKg.toDoubleOrNull() ?: 0.0
                            )
                            rutinaActualizada?.let {
                                db.rutinaDao().actualizarRutina(it)
                                navController.popBackStack()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                ) {
                    Text("Guardar Cambios")
                }

                Button(
                    onClick = {
                        scope.launch {
                            db.rutinaDao().buscarPorId(rutinaId)?.let {
                                db.rutinaDao().eliminarRutina(it)
                                navController.popBackStack()
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                ) {
                    Text("Eliminar Rutina", color = Color.White)
                }

                TextButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cancelar")
                }
            }
        }
    }
}
