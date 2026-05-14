package com.semana06.gymtrackerpro.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ListaRutinasScreen(
    navController: NavController,
    usuarioId: Int
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Lista de Rutinas - Usuario ID: $usuarioId")

        Card(
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Rutina de prueba")
                Text("Ejercicio: Press banca")
                Text("Series: 4")
                Text("Repeticiones: 10")
                Text("Peso: 50 kg")

                Button(
                    onClick = { navController.navigate("detalle_rutina/1") },
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
                ) {
                    Text("Editar Rutina")
                }
            }
        }

        Button(
            onClick = { navController.navigate("agregar_rutina/$usuarioId") },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("Agregar Rutina")
        }

        TextButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}