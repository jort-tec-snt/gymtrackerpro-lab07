package com.semana06.gymtrackerpro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.semana06.gymtrackerpro.navigation.AppNavigation
import com.semana06.gymtrackerpro.ui.theme.GymTrackerProTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            GymTrackerProTheme {
                AppNavigation()
            }
        }
    }
}