package com.autism.figuritas.persistence.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * Class of level for database
 */
@Entity(tableName = "tb_level")
public class Nivel
{
    @PrimaryKey()
    @ColumnInfo(name = "id_level")
    int id_nivel;

    @ColumnInfo(name = "title")
    String titulo;

    @ColumnInfo(name = "description")
    String descripcion;

    @ForeignKey(entity = Configuracion.class, parentColumns = "id_Config", childColumns = "id_config")
    @ColumnInfo(name = "id_config")
    int id_config;

    public Nivel(int id_nivel, String titulo, String descripcion, int id_config)
    {
        this.id_nivel = id_nivel;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.id_config = id_config;
    }
}