package com.autism.figuritas.persistence.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Class of User config for database
 */
@Entity(tableName = "tb_config_user")
public class Configuracion
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_Config")
    int id_config;

    @ColumnInfo(name = "difficulty")
    byte dificultad;

    @ColumnInfo(name = "music")
    boolean musica;

    @ColumnInfo(name = "sound")
    boolean sonido;

    @ColumnInfo(name = "colour")
    String color;

    public Configuracion(byte dificultad, boolean musica, boolean sonido, String color)
    {
        this.dificultad = dificultad;
        this.musica = musica;
        this.sonido = sonido;
        this.color = color;
    }
}
