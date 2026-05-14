package com.semana06.gymtrackerpro.screens
import com.semana06.gymtrackerpro.navigation.Routes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MenuPrincipalScreen(
    navController: NavController,
    usuarioId: Int
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Menú Principal - Usuario ID: $usuarioId")

        Button(
            onClick = { navController.navigate("agregar_rutina/$usuarioId") },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("Agregar Rutina")
        }

        Button(
            onClick = { navController.navigate("lista_rutinas/$usuarioId") },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("Lista Rutinas")
        }

        Button(
            onClick = { navController.navigate("perfil_usuario/$usuarioId") },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("Perfil Usuario")
        }

        Button(
            onClick = { navController.navigate(Routes.LOGIN) },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("Cerrar Sesión")
        }
    }
}