package com.autism.figuritas;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.room.Room;

import com.autism.figuritas.persistence.database.DataBase;
import com.autism.figuritas.persistence.preferences.ConstantPreferences;

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
        if(!sharedPreferences.contains(ConstantPreferences.CURRENT_USER))
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putLong(ConstantPreferences.CURRENT_USER, 0);
            editor.putBoolean(ConstantPreferences.CONFIG_COMPLETE, false);
            editor.putInt(ConstantPreferences.MUSIC_VOLUME, 40);
            editor.putInt(ConstantPreferences.SOUND_VOLUME, 60);
            editor.putBoolean(ConstantPreferences.INIT_SESSION_AUTOMATIC, false);
            editor.commit();
        }
    }

    public DataBase getDataBase()
    {
        return this.dataBase;
    }
}
