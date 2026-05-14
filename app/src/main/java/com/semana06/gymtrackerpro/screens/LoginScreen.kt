package com.semana06.gymtrackerpro.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.semana06.gymtrackerpro.navigation.Routes

@Composable
fun LoginScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Login Screen")

        Button(
            onClick = { navController.navigate(Routes.REGISTRO) },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("Ir a Registro")
        }

        Button(
            onClick = { navController.navigate("menu/1") },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
        ) {
            Text("Entrar al Menú")
        }
    }
}