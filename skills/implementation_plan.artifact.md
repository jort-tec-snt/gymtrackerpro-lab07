# Plan de Implementación: Persistencia de Datos con Room

La base de datos aparece como "(closed)" porque el código actual de las pantallas no la está utilizando. Room abre la base de datos solo cuando se realiza una operación (lectura o escritura). Actualmente, los botones de "Registrarse" y "Guardar Rutina" solo navegan entre pantallas sin interactuar con `AppDatabase`.

Este plan implementará la lógica necesaria para guardar y recuperar datos en la base de datos.

## Cambios Propuestos

### Componente de Datos

#### [Rutina.kt](file:///C:/TDYDS/CICLOIV/03PM/lab07/app/src/main/java/com/semana06/gymtrackerpro/data/Rutina.kt)
- Corregir el nombre del campo `ejercicio` (actualmente está como `ejercicio` pero en algunas partes se usaba `nombreEjercicio`).

#### [RutinaDao.kt](file:///C:/TDYDS/CICLOIV/03PM/lab07/app/src/main/java/com/semana06/gymtrackerpro/data/RutinaDao.kt)
- Asegurar que los nombres de los métodos y parámetros coincidan con la entidad.

---

### Componente de UI (Screens)

#### [RegistroScreen.kt](file:///C:/TDYDS/CICLOIV/03PM/lab07/app/src/main/java/com/semana06/gymtrackerpro/screens/RegistroScreen.kt)
- Inyectar o instanciar `AppDatabase`.
- Implementar la lógica para guardar un nuevo `Usuario` al hacer clic en "Registrarse" usando `rememberCoroutineScope`.

#### [LoginScreen.kt](file:///C:/TDYDS/CICLOIV/03PM/lab07/app/src/main/java/com/semana06/gymtrackerpro/screens/LoginScreen.kt)
- Añadir campos de texto para `usuario` y `password`.
- Validar las credenciales contra la base de datos antes de navegar al menú.

#### [AgregarRutinaScreen.kt](file:///C:/TDYDS/CICLOIV/03PM/lab07/app/src/main/java/com/semana06/gymtrackerpro/screens/AgregarRutinaScreen.kt)
- Implementar el guardado de la `Rutina` en la base de datos asociada al `usuarioId`.

#### [ListaRutinasScreen.kt](file:///C:/TDYDS/CICLOIV/03PM/lab07/app/src/main/java/com/semana06/gymtrackerpro/screens/ListaRutinasScreen.kt)
- Recuperar la lista real de rutinas desde la base de datos usando `LaunchedEffect` o `collectAsState`.
- Mostrar las rutinas reales en lugar del ejemplo estático.

#### [DetalleRutinaScreen.kt](file:///C:/TDYDS/CICLOIV/03PM/lab07/app/src/main/java/com/semana06/gymtrackerpro/screens/DetalleRutinaScreen.kt)
- Cargar los datos de la rutina seleccionada.
- Implementar las funciones de "Actualizar" y "Eliminar".

## Plan de Verificación

### Verificación Manual
1. **Registro:** Crear un usuario y verificar en el "Database Inspector" que aparezca en la tabla `usuarios`.
2. **Login:** Intentar entrar con el usuario creado.
3. **Agregar Rutina:** Guardar una rutina y verificar que aparezca en la tabla `rutinas`.
4. **Lista:** Comprobar que la pantalla "Lista de Rutinas" muestra los datos guardados.
5. **Database Inspector:** Confirmar que la base de datos ya no aparece como "(closed)" mientras la app está activa y realizando operaciones.
