package com.autism.figuritas.persistence.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * Class of user for database
 */
@Entity(tableName = "tb_user")
public class Usuario
{
    @ColumnInfo(name = "id_user")
    @PrimaryKey(autoGenerate = true)
    int id_usuario;

    @ColumnInfo(name = "name")
    String nombre;

    @ColumnInfo(name = "last_name_f")
    String apellido_paterno;

    @ColumnInfo(name = "last_name_m")
    String apellido_materno;

    @ColumnInfo(name = "age")
    byte edad;

    @ColumnInfo(name = "password")
    String contrasena;

    @ForeignKey(entity = Configuracion.class, parentColumns = "idConfig", childColumns = "idconfig")
    @ColumnInfo(name = "id_config")
    int id_config;

    @ForeignKey(entity = Historial.class, parentColumns = "id_history", childColumns = "id_history")
    @ColumnInfo(name = "id_history")
    int id_historial;

    public Usuario(String nombre, String apellido_paterno, String apellido_materno, byte edad, String contrasena, int id_config, int id_historial)
    {
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.edad = edad;
        this.contrasena = contrasena;
        this.id_config = id_config;
        this.id_historial = id_historial;
    }
}
