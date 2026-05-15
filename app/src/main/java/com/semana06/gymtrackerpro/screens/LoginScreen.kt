package com.semana06.gymtrackerpro.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.semana06.gymtrackerpro.data.AppDatabase
import com.semana06.gymtrackerpro.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {
    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF1A237E), Color.Black)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visibleState = visibleState,
            enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 })
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Default.FitnessCenter,
                    contentDescription = "Gym Icon",
                    modifier = Modifier.size(100.dp),
                    tint = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "GymTracker Pro",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))

                OutlinedTextField(
                    value = usuario,
                    onValueChange = { usuario = it },
                    label = { Text("Usuario", color = Color.LightGray) },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Color.White) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFF42A5F5),
                        unfocusedBorderColor = Color.Gray
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña", color = Color.LightGray) },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Color.White) },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFF42A5F5),
                        unfocusedBorderColor = Color.Gray
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(32.dp))

                if (errorMessage != null) {
                    Text(
                        text = errorMessage!!,
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }

                Button(
                    onClick = {
                        if (usuario.isBlank() || password.isBlank()) {
                            errorMessage = "Por favor, complete todos los campos"
                            return@Button
                        }
                        scope.launch {
                            val db = AppDatabase.getDatabase(context)
                            val user = db.usuarioDao().buscarPorCredenciales(usuario, password)
                            if (user != null) {
                                navController.navigate("menu/${user.id}")
                            } else {
                                errorMessage = "Usuario o contraseña incorrectos"
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(Color(0xFF2196F3), Color(0xFF0D47A1))
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    shape = RoundedCornerShape(16.dp),
                    contentPadding = PaddingValues()
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("ENTRAR", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(
                    onClick = { navController.navigate(Routes.REGISTRO) }
                ) {
                    Text(
                        "¿No tienes cuenta? Regístrate aquí",
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}
