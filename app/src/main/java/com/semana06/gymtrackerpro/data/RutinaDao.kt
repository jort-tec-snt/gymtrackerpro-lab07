package com.semana06.gymtrackerpro.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RutinaDao {

    @Insert
    suspend fun insertarRutina(rutina: Rutina)

    @Update
    suspend fun actualizarRutina(rutina: Rutina)

    @Delete
    suspend fun eliminarRutina(rutina: Rutina)

    @Query("SELECT * FROM rutinas WHERE usuario_id = :usuarioId ORDER BY id DESC")
    suspend fun listarPorUsuario(usuarioId: Int): List<Rutina>

    @Query("SELECT * FROM rutinas WHERE id = :id LIMIT 1")
    suspend fun buscarPorId(id: Int): Rutina?

    @Query("SELECT COUNT(*) FROM rutinas WHERE usuario_id = :usuarioId")
    suspend fun contarRutinas(usuarioId: Int): Int

    @Query("SELECT SUM(peso_kg * series * repeticiones) FROM rutinas WHERE usuario_id = :usuarioId")
    suspend fun calcularPesoTotal(usuarioId: Int): Double?
}