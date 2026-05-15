
---

# 2. `.skills/frontend-compose-rules.md`
    
# Skill: Reglas Front-End Jetpack Compose

## Rol de este skill

Este skill define cómo mejorar visualmente las pantallas de GymTracker Pro sin alterar la lógica principal.

## Objetivo

Mejorar el front-end usando Jetpack Compose y Material 3 de forma limpia, académica y fácil de explicar.

## Reglas generales de UI

Cada pantalla debe tener:

- Estructura clara.
- Padding externo entre `16.dp` y `24.dp`.
- Título visible.
- Componentes centrados cuando sea pantalla de formulario.
- Botones con ancho completo cuando sean acciones principales.
- Cards para agrupar información.
- Textos legibles usando `MaterialTheme.typography`.
- Separación vertical coherente usando `padding(top = 12.dp)` o `Spacer`.

## Componentes permitidos

Usar preferentemente:

```kotlin
Scaffold
TopAppBar
Card
Column
Row
LazyColumn
Button
TextButton
OutlinedTextField
FloatingActionButton
AlertDialog
SnackbarHost
ModalNavigationDrawer
```
Componentes a evitar

Evitar:

```evitar
XML layouts
Fragments
AppCompat views
Librerías visuales externas innecesarias
Arquitecturas complejas
```
### Estilo recomendado
- Login y Registro: diseño centrado con Card. 
- Menú principal: Scaffold + TopAppBar + opciones principales. 
- Lista de rutinas: LazyColumn + Card por rutina. 
- Agregar Rutina: formulario con OutlinedTextField. 
- Detalle Rutina: formulario precargado para edición. 
- Perfil: Card con datos del usuario y estadísticas.

### Regla de modificación segura
Al mejorar una pantalla:

1. Conservar el nombre de la función @Composable.
Conservar los parámetros existentes.
No eliminar NavController.
No romper rutas existentes.
No modificar Room si la tarea solo pide mejorar front-end.
No cambiar nombres de entidades, DAO ni base de datos.