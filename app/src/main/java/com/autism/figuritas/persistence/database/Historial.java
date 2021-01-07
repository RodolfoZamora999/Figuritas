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
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_history")
    int id_historial;

    @ColumnInfo(name = "percentage")
    int porcentaje;

    @ColumnInfo(name = "description")
    String descripcion;

    public Historial(int porcentaje, String descripcion)
    {
        this.porcentaje = porcentaje;
        this.descripcion = descripcion;
    }
}
