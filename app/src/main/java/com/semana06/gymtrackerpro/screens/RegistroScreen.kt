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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun RegistroScreen(
    navController: NavController
) {
    val nombreUsuario = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val mostrarPassword = remember { mutableStateOf(false) }
    val nombreCompleto = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val edad = remember { mutableStateOf("") }

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
                Text("Registro de Usuario")

                OutlinedTextField(
                    value = nombreUsuario.value,
                    onValueChange = { nombreUsuario.value = it },
                    label = { Text("Nombre de usuario") },
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
                )

                OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text("Contraseña") },
                    isError = password.value.isNotEmpty() && password.value.length < 8,
                    supportingText = {
                        if (password.value.isNotEmpty() && password.value.length < 8) {
                            Text("La contraseña debe tener mínimo 8 caracteres")
                        }
                    },
                    visualTransformation = if (mostrarPassword.value) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                mostrarPassword.value = !mostrarPassword.value
                            }
                        ) {
                            Icon(
                                imageVector = if (mostrarPassword.value) {
                                    Icons.Default.VisibilityOff
                                } else {
                                    Icons.Default.Visibility
                                },
                                contentDescription = "Mostrar u ocultar contraseña"
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                )

                OutlinedTextField(
                    value = nombreCompleto.value,
                    onValueChange = { nombreCompleto.value = it },
                    label = { Text("Nombre completo") },
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
                )

                OutlinedTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
                )

                OutlinedTextField(
                    value = edad.value,
                    onValueChange = { edad.value = it },
                    label = { Text("Edad") },
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp)
                )

                Button(
                    onClick = {
                        if (password.value.length >= 8) {
                            navController.popBackStack()
                        }
                    },
                    enabled = password.value.length >= 8,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text("Registrarse")
                }

                TextButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Volver al Login")
                }
            }
        }
    }
}