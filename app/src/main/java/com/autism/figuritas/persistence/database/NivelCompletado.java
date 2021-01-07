package com.autism.figuritas.persistence.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * Class of Complete level for database
 */
@Entity(tableName = "tb_level_complete")
public class NivelCompletado
{
    @PrimaryKey
    @ColumnInfo(name = "id_levelcomplete")
    int id_nivel_completado;

    @ColumnInfo(name = "status")
    String estatus;

    @ColumnInfo(name = "score")
    String puntuacion;

    @ColumnInfo(name = "observation")
    String observacion;

    @ForeignKey(entity = Nivel.class, parentColumns = "id_level", childColumns = "id_level")
    @ColumnInfo(name = "id_level")
    int id_nivel;

    @ForeignKey(entity = Historial.class, parentColumns = "id_history", childColumns = "id_history")
    @ColumnInfo(name = "id_history")
    int id_historial;

    @ForeignKey(entity = Bonificacion.class, parentColumns = "id_bonus", childColumns = "id_bonus")
    @ColumnInfo(name = "id_bonus")
    int id_bonificacion;

    public NivelCompletado(int id_nivel_completado, String estatus, String puntuacion, String observacion,
                           int id_nivel, int id_historial, int id_bonificacion)
    {
        this.id_nivel_completado = id_nivel_completado;
        this.estatus = estatus;
        this.puntuacion = puntuacion;
        this.observacion = observacion;
        this.id_nivel = id_nivel;
        this.id_historial = id_historial;
        this.id_bonificacion = id_bonificacion;
    }
}
