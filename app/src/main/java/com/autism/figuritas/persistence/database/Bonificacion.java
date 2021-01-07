package com.autism.figuritas.persistence.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Class of bonus for database
 */
@Entity(tableName = "tb_bonus")
public class Bonificacion
{
    @PrimaryKey
    @ColumnInfo(name = "id_bonus")
    public long id_bonificacion;

    @ColumnInfo(name = "bonus_accumulate")
    public int bonificacion_acumulada;

    public Bonificacion(long id_bonificacion, int bonificacion_acumulada)
    {
        this.id_bonificacion = id_bonificacion;
        this.bonificacion_acumulada = bonificacion_acumulada;
    }

    public Bonificacion()
    {

    }
}
