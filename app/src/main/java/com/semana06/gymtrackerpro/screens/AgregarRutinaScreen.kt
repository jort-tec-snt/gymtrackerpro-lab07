package com.semana06.gymtrackerpro.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AgregarRutinaScreen(
    navController: NavController,
    usuarioId: Int
) {
    val ejercicio = remember { mutableStateOf("") }
    val grupoMuscular = remember { mutableStateOf("") }
    val series = remember { mutableStateOf("") }
    val repeticiones = remember { mutableStateOf("") }
    val pesoKg = remember { mutableStateOf("") }
    val fecha = remember { mutableStateOf("") }

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
                Text("Agregar Rutina - Usuario ID: $usuarioId")

                OutlinedTextField(
                    value = ejercicio.value,
                    onValueChange = { ejercicio.value = it },
                    label = { Text("Ejercicio") },
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
                )

                OutlinedTextField(
                    value = grupoMuscular.value,
                    onValueChange = { grupoMuscular.value = it },
                    label = { Text("Grupo muscular") },
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
                )

                OutlinedTextField(
                    value = series.value,
                    onValueChange = { series.value = it },
                    label = { Text("Series") },
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
                )

                OutlinedTextField(
                    value = repeticiones.value,
                    onValueChange = { repeticiones.value = it },
                    label = { Text("Repeticiones") },
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
                )

                OutlinedTextField(
                    value = pesoKg.value,
                    onValueChange = { pesoKg.value = it },
                    label = { Text("Peso Kg") },
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
                )

                OutlinedTextField(
                    value = fecha.value,
                    onValueChange = { fecha.value = it },
                    label = { Text("Fecha") },
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
                )

                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                ) {
                    Text("Guardar Rutina")
                }

                TextButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Volver")
                }
            }
        }
    }
}