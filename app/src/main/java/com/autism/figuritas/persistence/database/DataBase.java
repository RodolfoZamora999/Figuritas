package com.autism.figuritas.persistence.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Create a database
 */
@Database(entities = {Bonus.class, Configuration.class, History.class, Level.class,
        LevelComplete.class, Notification.class, User.class}, version = 1)
public abstract class DataBase  extends RoomDatabase
{
    public abstract DAO getDAO();

    public static final String NAME = "FiguritasDB";
}
