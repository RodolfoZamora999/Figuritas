package com.autism.figuritas.persistence.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * Class of Notification for database
 */
@Entity(tableName = "tb_notification")
public class Notification
{
    @PrimaryKey
    @ColumnInfo(name = "id_notification")
    public long id_notificacion;

    @ColumnInfo(name = "notification")
    public String notificacion;

    @ForeignKey(entity = Level.class, parentColumns = "id_nivel", childColumns = "id_level")
    @ColumnInfo(name = "id_level")
    public long id_nivel;

    public Notification(long id_notificacion, String notificacion, long id_nivel)
    {
        this.id_notificacion = id_notificacion;
        this.notificacion = notificacion;
        this.id_nivel = id_nivel;
    }

    public Notification()
    {

    }
}
