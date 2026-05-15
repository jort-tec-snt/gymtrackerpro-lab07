package com.semana06.gymtrackerpro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.semana06.gymtrackerpro.data.AppDatabase
import com.semana06.gymtrackerpro.data.Usuario
import com.semana06.gymtrackerpro.navigation.Routes

@Composable
fun PerfilUsuarioScreen(
    navController: NavController,
    usuarioId: Int
) {
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    
    var usuario by remember { mutableStateOf<Usuario?>(null) }
    var cantidadRutinas by remember { mutableIntStateOf(0) }
    var pesoTotal by remember { mutableDoubleStateOf(0.0) }

    LaunchedEffect(usuarioId) {
        usuario = db.usuarioDao().buscarPorId(usuarioId)
        cantidadRutinas = db.rutinaDao().contarRutinas(usuarioId)
        pesoTotal = db.rutinaDao().calcularPesoTotal(usuarioId) ?: 0.0
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
                Text(text = "Perfil de Usuario", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(16.dp))
                
                usuario?.let {
                    Text(text = "Nombre: ${it.nombreCompleto}")
                    Text(text = "Usuario: ${it.nombreUsuario}")
                    Text(text = "Email: ${it.email}")
                    Text(text = "Edad: ${it.edad} años")
                    Text(text = "Miembro desde: ${it.fechaRegistro}")
                } ?: Text("Cargando datos del usuario...")

                Divider(modifier = Modifier.padding(vertical = 16.dp))

                Text(text = "Estadísticas", style = MaterialTheme.typography.titleMedium)
                Text(text = "Rutinas registradas: $cantidadRutinas")
                Text(text = "Volumen total (Kg): $pesoTotal")

                Button(
                    onClick = {
                        navController.navigate(Routes.LOGIN) {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxWidth().padding(top = 24.dp)
                ) {
                    Text("Cerrar Sesión")
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
