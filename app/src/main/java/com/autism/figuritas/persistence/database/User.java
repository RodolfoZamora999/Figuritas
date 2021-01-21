package com.autism.figuritas.persistence.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * Class of user for database
 */
@Entity(tableName = "tb_user")
public class User
{
    @PrimaryKey
    @ColumnInfo(name = "id_user")
    public long id_usuario;

    @ColumnInfo(name = "name")
    public String nombre;

    @ColumnInfo(name = "last_name_f")
    public String apellido_paterno;

    @ColumnInfo(name = "last_name_m")
    public String apellido_materno;

    @ColumnInfo(name = "age")
    public byte edad;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "password")
    public String contrasena;

    @ForeignKey(entity = Configuration.class, parentColumns = "idConfig", childColumns = "idconfig")
    @ColumnInfo(name = "id_config")
    public long id_config;

    @ForeignKey(entity = History.class, parentColumns = "id_history", childColumns = "id_history")
    @ColumnInfo(name = "id_history")
    public long id_historial;

    @ForeignKey(entity = Bonus.class, parentColumns = "id_bonus", childColumns = "id_bonus")
    @ColumnInfo(name = "id_bonus")
    public long id_bonificacion;

    public User(long id_usuario, String nombre, String apellido_paterno, String apellido_materno,
                byte edad, String email, String contrasena, long id_config, long id_historial, long id_bonificacion)
    {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.edad = edad;
        this.contrasena = contrasena;
        this.id_config = id_config;
        this.id_historial = id_historial;
        this.email = email;
        this.id_bonificacion = id_bonificacion;
    }

    public User()
    {

    }
}
