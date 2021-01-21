package com.autism.figuritas.iu.levels;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.autism.figuritas.MyDatabaseApplication;
import com.autism.figuritas.R;
import com.autism.figuritas.persistence.database.Configuration;
import com.autism.figuritas.persistence.database.DataBase;
import com.autism.figuritas.persistence.preferences.ConstantPreferences;

public class LevelActivity extends AppCompatActivity
{
    private MediaPlayer mediaPlayerMusic;
    private Configuration configuration;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_level);

        this.mediaPlayerMusic =  mediaPlayerMusic = MediaPlayer.create(this, R.raw.ambient_music);

        //Load Configuration from Database
        loadConfig();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        hideSystemUI();

        if(mediaPlayerMusic != null)
            mediaPlayerMusic.start();

    }

    @Override
    protected void onPause()
    {
        super.onPause();

        if(mediaPlayerMusic != null)
            if (mediaPlayerMusic.isPlaying())
                mediaPlayerMusic.pause();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        //Kill MediaPlayer
        if(mediaPlayerMusic != null)
        {
            if(mediaPlayerMusic.isPlaying())
                mediaPlayerMusic.stop();

            mediaPlayerMusic.release();
            mediaPlayerMusic = null;
        }
    }

    /**
     * Method to load config from database
     */
    protected void loadConfig()
    {
        //Get current id user from SharedPreferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final long currentUser = preferences.getLong(ConstantPreferences.CURRENT_USER, 0);
        final int levelMusic = preferences.getInt(ConstantPreferences.MUSIC_VOLUME, 40);
        final int levelSound = preferences.getInt(ConstantPreferences.SOUND_VOLUME, 60);


        DataBase dataBase = ((MyDatabaseApplication)getApplication()).getDataBase();

        dataBase.getQueryExecutor().execute(()->
        {
            configuration = dataBase.getDAO().getConfig(currentUser);
            configuration.volumeSound = levelSound;
            configuration.volumeMusic = levelMusic;

            mediaPlayerMusic.setVolume((configuration.volumeMusic * 0.01f),
                    (configuration.volumeMusic * 0.01f));
        });
    }

    /**
     * Very import: This method called after created activity in Fragment
     * Method to get Configuration
     */
    public Configuration getConfiguration()
    {
        return configuration;
    }

    /**
     * Method to get an instance of MediaPlayer Activity
     * @return MediaPlayer Activity Level
     */
    public MediaPlayer getMediaPlayerMusic()
    {
        if(mediaPlayerMusic != null)
            return mediaPlayerMusic;

        mediaPlayerMusic = MediaPlayer.create(this, R.raw.ambient_music);

        return mediaPlayerMusic;
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