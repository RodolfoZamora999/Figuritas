package com.autism.figuritas.persistence.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * Class of Complete level for database
 */
@Entity(tableName = "tb_level_complete")
public class LevelComplete
{
    @PrimaryKey
    @ColumnInfo(name = "id_levelcomplete")
    public long id_nivel_completado;

    @ColumnInfo(name = "status")
    public String estatus;

    @ColumnInfo(name = "score")
    public String puntuacion;

    @ColumnInfo(name = "observation")
    public String observacion;

    @ForeignKey(entity = Level.class, parentColumns = "id_level", childColumns = "id_level")
    @ColumnInfo(name = "id_level")
    public long id_nivel;

    @ForeignKey(entity = History.class, parentColumns = "id_history", childColumns = "id_history")
    @ColumnInfo(name = "id_history")
    public long id_historial;

    @ForeignKey(entity = Bonus.class, parentColumns = "id_bonus", childColumns = "id_bonus")
    @ColumnInfo(name = "id_bonus")
    public long id_bonificacion;

    public LevelComplete(long id_nivel_completado, String estatus, String puntuacion, String observacion,
                         long id_nivel, long id_historial, long id_bonificacion)
    {
        this.id_nivel_completado = id_nivel_completado;
        this.estatus = estatus;
        this.puntuacion = puntuacion;
        this.observacion = observacion;
        this.id_nivel = id_nivel;
        this.id_historial = id_historial;
        this.id_bonificacion = id_bonificacion;
    }

    public LevelComplete()
    {

    }
}
