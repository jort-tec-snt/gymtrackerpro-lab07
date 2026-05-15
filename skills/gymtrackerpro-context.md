# Skill: Contexto base de GymTracker Pro

## Rol de este skill

Este archivo define el contexto fijo del proyecto.  
Debe ser leído antes de modificar cualquier pantalla.

## Proyecto

Nombre de la app: GymTracker Pro.

Aplicación Android académica para registrar usuarios, iniciar sesión y gestionar rutinas de entrenamiento usando almacenamiento local.

## Stack obligatorio

- Kotlin
- Android Studio
- Jetpack Compose
- Material 3
- Navigation Compose
- Room SQLite clásico: `androidx.room`
- KSP
- Gradle Kotlin DSL

## Package principal

```kotlin
package com.semana06.gymtrackerpro

Arquitectura actual
com.semana06.gymtrackerpro
├── data
│   ├── AppDatabase.kt
│   ├── Usuario.kt
│   ├── UsuarioDao.kt
│   ├── Rutina.kt
│   └── RutinaDao.kt
│
├── navigation
│   ├── Routes.kt
│   └── AppNavigation.kt
│
├── screens
│   ├── LoginScreen.kt
│   ├── RegistroScreen.kt
│   ├── MenuPrincipalScreen.kt
│   ├── AgregarRutinaScreen.kt
│   ├── ListaRutinasScreen.kt
│   ├── DetalleRutinaScreen.kt
│   └── PerfilUsuarioScreen.kt
│
└── ui.theme
```
#### Pantallas existentes
- LoginScreen 
- RegistroScreen 
- MenuPrincipalScreen 
- AgregarRutinaScreen 
- ListaRutinasScreen 
- DetalleRutinaScreen 
- PerfilUsuarioScreen 

#### Reglas obligatorias
- No cambiar el package principal. 
- No cambiar nombres de pantallas. 
- No cambiar nombres de rutas sin actualizar Routes.kt y AppNavigation.kt. 
- No usar XML. 
- No usar Fragments. 
- No usar Hilt. 
- No usar Firebase. 
- No usar Retrofit. 
- No convertir el proyecto a MVVM avanzado salvo solicitud explícita. 
- Mantener código simple, funcional y explicable para examen. 
- Priorizar Jetpack Compose y Material 3.
