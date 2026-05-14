package com.semana06.gymtrackerpro.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {

    @Insert
    suspend fun insertarUsuario(usuario: Usuario)

    @Query("SELECT * FROM usuarios WHERE nombre_usuario = :nombreUsuario AND password = :password LIMIT 1")
    suspend fun buscarPorCredenciales(
        nombreUsuario: String,
        password: String
    ): Usuario?

    @Query("SELECT * FROM usuarios WHERE nombre_usuario = :nombreUsuario LIMIT 1")
    suspend fun buscarPorNombreUsuario(nombreUsuario: String): Usuario?

    @Query("SELECT * FROM usuarios WHERE email = :email LIMIT 1")
    suspend fun buscarPorEmail(email: String): Usuario?

    @Query("SELECT * FROM usuarios WHERE id = :id LIMIT 1")
    suspend fun buscarPorId(id: Int): Usuario?
}