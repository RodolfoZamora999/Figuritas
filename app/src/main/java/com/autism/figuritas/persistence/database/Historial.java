package com.autism.figuritas.persistence.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Class of history for database
 */
@Entity(tableName = "tb_history")
public class Historial
{
    @PrimaryKey
    @ColumnInfo(name = "id_history")
    public long id_historial;

    @ColumnInfo(name = "percentage")
    public int porcentaje;

    @ColumnInfo(name = "description")
    public String descripcion;

    public Historial(long id_historial, int porcentaje, String descripcion)
    {
        this.id_historial = id_historial;
        this.porcentaje = porcentaje;
        this.descripcion = descripcion;
    }

    public Historial()
    {

    }
}
