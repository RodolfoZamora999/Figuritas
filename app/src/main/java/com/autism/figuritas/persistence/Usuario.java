package com.autism.figuritas.persistence;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * Clase que representa a la entidad usuario de la base de datos.
 */
@Entity
public class Usuario
{
    @PrimaryKey
    int id_usuario;

    String nombre;

    String apellido_paterno;

    String apellido_materno;
}
