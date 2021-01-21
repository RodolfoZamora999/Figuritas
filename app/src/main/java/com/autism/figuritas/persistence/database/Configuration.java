package com.autism.figuritas.persistence.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Class of User config for database
 */
@Entity(tableName = "tb_config_user")
public class Configuration
{
    @PrimaryKey
    @ColumnInfo(name = "id_Config")
    public long id_config;

    @ColumnInfo(name = "difficulty")
    public byte dificultad;

    @ColumnInfo(name = "music")
    public boolean musica;

    @ColumnInfo(name = "sound")
    public boolean sonido;

    @ColumnInfo(name = "colour")
    public String color;

    //Attributes ignores to Database
    @Ignore
    public int volumeMusic;

    @Ignore
    public int volumeSound;

    public Configuration(long id_config, byte dificultad, boolean musica, boolean sonido, String color)
    {
        this.id_config = id_config;
        this.dificultad = dificultad;
        this.musica = musica;
        this.sonido = sonido;
        this.color = color;
    }

    public Configuration()
    {

    }
}
