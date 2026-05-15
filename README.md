
# GymTracker Pro
Integrantes: 

- Jeronimo Ortiz 
- Jason Gomez

## Descripción del proyecto

GymTracker Pro es una aplicación móvil desarrollada en Android Studio con Kotlin y Jetpack Compose, que permite a los usuarios registrarse, iniciar sesión y administrar rutinas de entrenamiento de forma local mediante Room y SQLite.<br>
El proyecto, correspondiente al Laboratorio 07 de Programación en Móviles, incluye navegación entre pantallas con Navigation Compose y operaciones CRUD para la gestión de rutinas.

## Objetivo

Desarrollar una aplicación móvil básica para un gimnasio, donde los usuarios puedan:

- Registrarse en la aplicación.
- Iniciar sesión.
- Acceder a un menú principal.
- Agregar rutinas de entrenamiento.
- Listar rutinas registradas.
- Editar rutinas existentes.
- Visualizar el perfil del usuario.
- Cerrar sesión.

## Tecnologías utilizadas

- Kotlin
- Android Studio
- Jetpack Compose
- Material 3
- Navigation Compose
- Room
- SQLite
- KSP
- Gradle Kotlin DSL
- Git y GitHub

## Estructura del proyecto
```text
app/src/main/java/com/semana06/gymtrackerpro/
│
├── data/
│   ├── AppDatabase.kt
│   ├── Usuario.kt
│   ├── Rutina.kt
│   ├── UsuarioDao.kt
│   └── RutinaDao.kt
│
├── navigation/
│   ├── AppNavigation.kt
│   └── Routes.kt
│
├── screens/
│   ├── LoginScreen.kt
│   ├── RegistroScreen.kt
│   ├── MenuPrincipalScreen.kt
│   ├── AgregarRutinaScreen.kt
│   ├── ListaRutinasScreen.kt
│   ├── DetalleRutinaScreen.kt
│   └── PerfilUsuarioScreen.kt
│
├── ui/theme/
│   ├── Color.kt
│   ├── Theme.kt
│   └── Type.kt
│
└── MainActivity.kt
```
## Módulo data
En el paquete data se encuentra toda la lógica relacionada con la base de datos local.

## Entidades
### Usuario
Representa la tabla de usuarios de la aplicación.

Campos principales:

- id
- nombre_usuario
- password
- nombre_completo
- email
- edad
- fecha_registro

### Rutina
Representa la tabla de rutinas de entrenamiento.

Campos principales:

- id 
- usuario_id 
- ejercicio 
- grupo_muscular 
- series
- repeticiones
- peso_kg
- fecha

## DAO (Data Access model)
Se implementaron interfaces DAO para acceder a la base de datos.

### UsuarioDao

Contiene consultas para:

- Insertar usuarios.
- Buscar usuario por credenciales.
- Buscar usuario por nombre de usuario.
- Buscar usuario por correo electrónico.
- Buscar usuario por ID.

### RutinaDao

Contiene consultas para:

- Insertar rutinas.
- Actualizar rutinas.
- Eliminar rutinas.
- Listar rutinas por usuario.
- Buscar rutina por ID.
- Contar rutinas registradas.
- Calcular el peso total levantado.

## Base de datos
La clase AppDatabase.kt configura Room mediante Room.databaseBuilder.<br>

Esta clase registra las entidades:
```text
Usuario::class
Rutina::class
```
También expone los DAO:

```text
usuarioDao()
rutinaDao()
```

## Módulo navigation
En el paquete navigation se gestiona la navegación de la aplicación.

### Routes.kt
Contiene las rutas principales de la aplicación:

```text
object Routes {
    const val LOGIN = "login"
    const val REGISTRO = "registro"
    const val MENU = "menu/{usuarioId}"
    const val AGREGAR_RUTINA = "agregar_rutina/{usuarioId}"
    const val LISTA_RUTINAS = "lista_rutinas/{usuarioId}"
    const val DETALLE_RUTINA = "detalle_rutina/{rutinaId}"
    const val PERFIL_USUARIO = "perfil_usuario/{usuarioId}"
}
```
### AppNavigation.kt
Contiene el NavHost principal de la aplicación.
Desde aquí se conectan las pantallas:

- LoginScreen 
- RegistroScreen 
- MenuPrincipalScreen 
- AgregarRutinaScreen 
- ListaRutinasScreen 
- DetalleRutinaScreen 
- PerfilUsuarioScreen

## Flujo de navegación

El flujo de navegación implementado sigue esta estructura:

```text
Login
 ├── Registro
 └── Menú Principal
       ├── Agregar Rutina
       ├── Lista Rutinas
       │     └── Detalle Rutina
       └── Perfil Usuario
              └── Cerrar sesión
```
## Paquete screens
El paquete screens contiene las pantallas visuales de la aplicación.

Hasta el momento se crearon las siguientes pantallas:

### LoginScreen
Pantalla inicial de la aplicación.

Estado actual:

- Interfaz inicial creada. 
- Navegación hacia Registro. 
- Navegación temporal hacia Menú Principal. 
- Pendiente conectar con Room para validar usuario real.

### RegistroScreen
Pantalla para registrar un nuevo usuario.

Estado actual:

1. Campos creados:
 - Nombre de usuario. 
 - Contraseña. 
 - Nombre completo.
 - Email. 
 - Edad.
2. Se agregó validación mínima de contraseña. 
3. La contraseña debe tener mínimo 8 caracteres. 
4. La contraseña se oculta por defecto. 
5. Se agregó opción para mostrar u ocultar contraseña. 
6. Pendiente guardar el usuario en Room.

### MenuPrincipalScreen

Pantalla principal después del inicio de sesión.

Estado actual:

1. Interfaz base creada. 
2. Navegación hacia:
- Agregar Rutina. 
- Lista Rutinas. 
- Perfil Usuario.
3. Pendiente mejorar con Scaffold, TopAppBar y Drawer.

### AgregarRutinaScreen

Pantalla para registrar una nueva rutina.

#### Campos creados:
```text
- ejercicio 
- grupo muscular 
- series 
- repeticiones 
- peso kg 
- fecha
```
Estado actual:

- Formulario visual creado. 
- Pendiente insertar datos reales en Room.

### ListaRutinasScreen

Pantalla para mostrar rutinas.

Estado actual:

- Interfaz base creada. 
- Se muestra una rutina temporal de prueba. 
- Se agregó navegación hacia DetalleRutinaScreen. 
- Pendiente listar rutinas reales desde Room. 
- Pendiente agregar eliminación con confirmación.

### DetalleRutinaScreen 

Pantalla para editar una rutina existente.

Estado actual:

- Interfaz de edición creada. 
- Campos temporales cargados. 
- Pendiente cargar datos reales desde Room. 
- Pendiente actualizar rutina en Room. 
- Pendiente eliminar rutina desde esta pantalla.

### PerfilUsuarioScreen

Pantalla para mostrar información del usuario.

Estado actual:

- Interfaz base creada. 
- Muestra el usuarioId. 
- Incluye botón de cerrar sesión. 
- Pendiente mostrar datos reales del usuario. 
- Pendiente mostrar estadísticas como cantidad de rutinas y peso total levantado.

## MEJORANDO CON IA LAS INTERFACES DE LAS VISTAS 

### PROMPT1: Mejora Visual de LoginScreen.kt 
```text
Mejora visualmente el LoginScreen de esta app Android con Jetpack Compose. 
Agrega un ícono de gimnasio o fitness en la parte superior, usa gradiente 
de fondo oscuro con colores azul/negro, campos de texto con estilo moderno 
(bordes redondeados, íconos de usuario y candado), botón con gradiente, 
y animación de entrada con fadeIn. Mantén toda la lógica existente.
```

### PROMPT2: Mejora Visual de MenuPrincipalScreen:
```text
Para MenuPrincipalScreen:
Mejora visualmente el MenuPrincipalScreen con Jetpack Compose. 
Las Cards del dashboard deben tener íconos grandes, colores diferentes 
para cada una (azul, verde, naranja), sombra elevada, y efecto al presionar. 
El Drawer debe tener header con avatar placeholder y nombre del usuario. 
Mantén toda la lógica de navegación existente.
```

### PROMPT3: Mejora Visual de ListaRutinasScreen:
```text
Para ListaRutinasScreen:
Mejora visualmente el ListaRutinasScreen. Cada Card de rutina debe mostrar 
un ícono de ejercicio, chips con series/repeticiones/peso con colores, 
y animación al eliminar. Si la lista está vacía muestra un mensaje ilustrado 
de "Sin rutinas aún". Mantén la lógica de delete con AlertDialog y el FAB.
```

### PROMPT4: Mejora Visual de AgregarRutinaScreen y DetalleRutinaScreen:
```text
Para AgregarRutinaScreen y DetalleRutinaScreen:
Mejora visualmente el formulario AgregarRutinaScreen/DetalleRutinaScreen. 
Usa secciones separadas por títulos, íconos en cada OutlinedTextField, 
colores de acento en el botón guardar, y un indicador visual de progreso 
o validación en tiempo real. Mantén toda la lógica Room existente.
```

### PROMPT5: Mejora Visual de PerfilUsuarioScreen:
```text
Para PerfilUsuarioScreen:
Mejora visualmente el PerfilUsuarioScreen. Agrega un avatar circular con 
iniciales del usuario, tarjetas de estadísticas con íconos y colores 
(total rutinas en azul, volumen en verde), y una sección de logros básicos. 
El botón de cerrar sesión debe ser rojo con ícono. Mantén la lógica existente.
```