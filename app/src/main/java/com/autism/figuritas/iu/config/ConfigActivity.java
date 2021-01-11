package com.autism.figuritas.iu.config;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import com.autism.figuritas.MyDatabaseApplication;
import com.autism.figuritas.R;
import com.autism.figuritas.persistence.database.Configuracion;
import com.autism.figuritas.persistence.database.DataBase;

public class ConfigActivity extends AppCompatActivity
{
    private DataBase database;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_config);


        //Get id config
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final long idConfig = preferences.getLong("current_user", 0);

        //Load database config
        database = ((MyDatabaseApplication)getApplication()).getDataBase();

        //Get data
        database.getQueryExecutor().execute(()->
        {
            final Configuracion configuracion = database.getDAO().getConfig(idConfig);
            runOnUiThread(()-> updateConfigIU(configuracion));
        });
    }

    /**
     * Method to update IU config
     * @param configuracion
     */
    private void updateConfigIU(Configuracion configuracion)
    {
        Log.d("PRINT", "id_config: "+ configuracion.id_config);
        Log.d("PRINT", "color: "+ configuracion.color);
        Log.d("PRINT", "music: "+ configuracion.musica);
        Log.d("PRINT", "sound: "+ configuracion.sonido);
        Log.d("PRINT", "color: "+ configuracion.dificultad);
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        hideSystemUI();
    }

    /**
     * Do fullscreen activity
     */
    private void hideSystemUI()
    {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}