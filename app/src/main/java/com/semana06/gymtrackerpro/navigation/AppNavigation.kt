package com.semana06.gymtrackerpro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.semana06.gymtrackerpro.screens.AgregarRutinaScreen
import com.semana06.gymtrackerpro.screens.DetalleRutinaScreen
import com.semana06.gymtrackerpro.screens.ListaRutinasScreen
import com.semana06.gymtrackerpro.screens.LoginScreen
import com.semana06.gymtrackerpro.screens.MenuPrincipalScreen
import com.semana06.gymtrackerpro.screens.PerfilUsuarioScreen
import com.semana06.gymtrackerpro.screens.RegistroScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {

        // LOGIN
        composable(Routes.LOGIN) {

            LoginScreen(navController)

        }

        // REGISTRO
        composable(Routes.REGISTRO) {

            RegistroScreen(navController)

        }

        // MENU
        composable(
            route = Routes.MENU,
            arguments = listOf(
                navArgument("usuarioId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val usuarioId =
                backStackEntry.arguments?.getInt("usuarioId") ?: 0

            MenuPrincipalScreen(
                navController = navController,
                usuarioId = usuarioId
            )
        }

        // AGREGAR RUTINA
        composable(
            route = Routes.AGREGAR_RUTINA,
            arguments = listOf(
                navArgument("usuarioId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val usuarioId =
                backStackEntry.arguments?.getInt("usuarioId") ?: 0

            AgregarRutinaScreen(
                navController = navController,
                usuarioId = usuarioId
            )
        }

        // LISTA RUTINAS
        composable(
            route = Routes.LISTA_RUTINAS,
            arguments = listOf(
                navArgument("usuarioId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val usuarioId =
                backStackEntry.arguments?.getInt("usuarioId") ?: 0

            ListaRutinasScreen(
                navController = navController,
                usuarioId = usuarioId
            )
        }

        // DETALLE RUTINA
        composable(
            route = Routes.DETALLE_RUTINA,
            arguments = listOf(
                navArgument("rutinaId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val rutinaId =
                backStackEntry.arguments?.getInt("rutinaId") ?: 0

            DetalleRutinaScreen(
                navController = navController,
                rutinaId = rutinaId
            )
        }

        // PERFIL USUARIO
        composable(
            route = Routes.PERFIL_USUARIO,
            arguments = listOf(
                navArgument("usuarioId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val usuarioId =
                backStackEntry.arguments?.getInt("usuarioId") ?: 0

            PerfilUsuarioScreen(
                navController = navController,
                usuarioId = usuarioId
            )
        }
    }
}