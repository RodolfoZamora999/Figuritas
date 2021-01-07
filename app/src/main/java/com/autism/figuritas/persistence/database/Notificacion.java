package com.autism.figuritas.persistence.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * Class of Notification for database
 */
@Entity(tableName = "tb_notification")
public class Notificacion
{
    @PrimaryKey
    @ColumnInfo(name = "id_notification")
    int id_notificacion;

    @ColumnInfo(name = "notification")
    String notificacion;

    @ForeignKey(entity = Nivel.class, parentColumns = "id_nivel", childColumns = "id_level")
    @ColumnInfo(name = "id_level")
    int id_nivel;

    public Notificacion(int id_notificacion, String notificacion, int id_nivel)
    {
        this.id_notificacion = id_notificacion;
        this.notificacion = notificacion;
        this.id_nivel = id_nivel;
    }
}
