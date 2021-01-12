package com.autism.figuritas;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.room.Room;

import com.autism.figuritas.persistence.database.DataBase;

public class MyDatabaseApplication extends Application
{
    private DataBase dataBase;

    @Override
    public void onCreate()
    {
        super.onCreate();

        //Create database
        this.dataBase = Room.databaseBuilder(this, DataBase.class, DataBase.NAME).build();

        //Create values for SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        //Check values
        if(!sharedPreferences.contains("current_user") || !sharedPreferences.contains("config_complete"))
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putLong("current_user", 0);
            editor.putBoolean("config_complete", false);
            editor.putInt("music_volume", 40);
            editor.putInt("sound_volume", 60);
            editor.commit();
        }
    }

    public DataBase getDataBase()
    {
        return this.dataBase;
    }
}
