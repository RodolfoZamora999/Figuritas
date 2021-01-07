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
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_bonus")
    int id_bonificacion;

    @ColumnInfo(name = "bonus_accumulate")
    int bonificacion_acumulada;

    public Bonificacion(int bonificacion_acumulada)
    {
        this.bonificacion_acumulada = bonificacion_acumulada;
    }
}
