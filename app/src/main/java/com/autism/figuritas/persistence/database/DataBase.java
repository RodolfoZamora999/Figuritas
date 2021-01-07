package com.autism.figuritas.persistence.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Create a database
 */
@Database(entities = {Bonificacion.class, Configuracion.class, Historial.class, Nivel.class,
        NivelCompletado.class, Notificacion.class, Usuario.class}, version = 1)
public abstract class DataBase  extends RoomDatabase
{
    public abstract DAO getDAO();

    public static final String NAME = "FiguritasDB";
}
