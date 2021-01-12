package com.autism.figuritas.iu.config;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;
import com.autism.figuritas.MyDatabaseApplication;
import com.autism.figuritas.R;
import com.autism.figuritas.persistence.database.Configuracion;
import com.autism.figuritas.persistence.database.DataBase;

public class ConfigActivity extends AppCompatActivity
{
    private MediaPlayer mediaPlayerMusic;
    private MediaPlayer mediaPlayerSound;

    private DataBase database;

    private ImageButton btnRegresar;
    private ImageButton btnSave;
    private ImageButton btnSound;

    private Button btnLevel1;
    private Button btnLevel2;
    private Button btnLevel3;

    private SeekBar seekBarMusic;
    private SeekBar seekBarSound;

    private ImageView imgColor;

    private View lastView;

    private int level;
    private String currentColor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_config);

        Toolbar toolbar = findViewById(R.id.toolbarConfig);
        //setSupportActionBar(toolbar);
       // getSupportActionBar().setTitle(R.string.configuracion);

        //Reference
        this.btnRegresar = findViewById(R.id.btnRegresarConfig);
        this.btnSave = findViewById(R.id.btnGuardarConfig);
        this.btnLevel1 = findViewById(R.id.btnLevelConfig1);
        this.btnLevel2 = findViewById(R.id.btnLevelConfig2);
        this.btnLevel3 = findViewById(R.id.btnLevelConfig3);
        this.imgColor = findViewById(R.id.imgColorConfig);
        this.btnSound = findViewById(R.id.btnSoundNowConfig);
        this.seekBarMusic = findViewById(R.id.seekBarMusicaConfig);
        this.seekBarSound = findViewById(R.id.seekBarSoundConfig);

        //Event to Click event
        this.btnRegresar.setOnClickListener(view -> onBackPressed());
        this.btnSave.setOnClickListener(view ->  saveConfigDatabase(view));
        this.btnLevel1.setOnClickListener(view -> springAnimation(view, 0));
        this.btnLevel2.setOnClickListener(view -> springAnimation(view, 1));
        this.btnLevel3.setOnClickListener(view -> springAnimation(view, 2));
        this.btnSound.setOnClickListener(view -> playSound(view));

        //Events to SeekBars
        this.seekBarMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                if(mediaPlayerMusic != null)
                    mediaPlayerMusic.setVolume((i * 0.01f),(i * 0.01f));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        this.seekBarSound.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                if(mediaPlayerSound != null)
                    mediaPlayerSound.setVolume((i * 0.01f),(i * 0.01f));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        //Create MediaPlayerInstance
        this.mediaPlayerMusic = MediaPlayer.create(this, R.raw.ambient_music);
        this.mediaPlayerSound = MediaPlayer.create(this, R.raw.cuadrado);

        //Load database config
        loadConfigDatabase();
    }

    /**
     * Method to update IU config
     * @param configuracion
     */
    private void updateConfigIU(Configuracion configuracion, int volumeMusic, int volumeSound)
    {
        this.mediaPlayerMusic.setVolume((volumeMusic * 0.01f), (volumeMusic * 0.01f));
        this.mediaPlayerSound.setVolume((volumeMusic * 0.01f), (volumeMusic * 0.01f));
        this.seekBarMusic.setProgress(volumeMusic);
        this.seekBarSound.setProgress(volumeSound);

        switch (configuracion.color)
        {
            case "#FFC107":
               imgColor.setBackgroundResource(R.color.AMBER);
               currentColor = "#FFC107";
                break;
            case "#00BCD4":
               imgColor.setBackgroundResource(R.color.CYAN);
               currentColor = "00BCD4";
                break;
            case "#FF4081":
                imgColor.setBackgroundResource(R.color.PINK);
                currentColor = "#FF4081";
                break;
            case "#FF8000":
                imgColor.setBackgroundResource(R.color.ORANGE);
                currentColor = "#FF8000";
                break;
            case "#F44336":
                imgColor.setBackgroundResource(R.color.RED);
                currentColor = "#F44336";
                break;
            case "#ACAF50":
                imgColor.setBackgroundResource(R.color.GREEN);
                currentColor = "#ACAF50";
                break;
            case "#03A9F4":
               imgColor.setBackgroundResource(R.color.LIGHT_BLUE);
                currentColor = "#03A9F4";
                break;
            case "#FFFFFF":
                imgColor.setBackgroundResource(R.color.WHITE);
                currentColor = "#FFFFFF";
                break;
            default:
                imgColor.setBackgroundResource(R.color.design_default_color_background);
                currentColor = "#FFFFFF";
                break;
        }

        switch (configuracion.dificultad)
        {
            case 0:
                btnLevel1.setRotationX(60f);
                lastView = btnLevel1;
                level = 0;
                break;
            case 1:
                btnLevel2.setRotationX(60f);
                lastView = btnLevel2;
                level = 1;
                break;
            case 2:
                btnLevel3.setRotationX(60f);
                lastView = btnLevel3;
                level = 2;
                break;
        }
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
            if(mediaPlayerMusic.isPlaying())
                mediaPlayerMusic.pause();

            if(mediaPlayerSound != null)
                if(mediaPlayerSound.isPlaying())
                    mediaPlayerSound.stop();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //Close MediaPlayer
        if (mediaPlayerMusic != null)
        {
            if(mediaPlayerMusic.isPlaying())
                mediaPlayerMusic.stop();

            mediaPlayerMusic.release();
            mediaPlayerMusic = null;
        }

        if (mediaPlayerSound != null)
        {
            if(mediaPlayerSound.isPlaying())
                mediaPlayerSound.stop();

            mediaPlayerSound.release();
            mediaPlayerSound = null;
        }
    }

    /**
     * Method for load Config from Database
     */
    private void loadConfigDatabase()
    {
        database = ((MyDatabaseApplication)getApplication()).getDataBase();

        //Get data
        database.getQueryExecutor().execute(()->
        {
            //Get id config
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            final long idConfig = preferences.getLong("current_user", 0);
            int volumeMusic = preferences.getInt("music_volume", 40);
            int volumeSound = preferences.getInt("sound_volume", 70);

            final Configuracion configuracion = database.getDAO().getConfig(idConfig);

            runOnUiThread(()->
                    updateConfigIU(configuracion, volumeMusic, volumeSound));
        });
    }

    /**
     * Method for save Config in Database
     * @param view
     */
    private void saveConfigDatabase(View view)
    {
        DataBase dataBase = ((MyDatabaseApplication)getApplication()).getDataBase();
        dataBase.getQueryExecutor().execute(()->
        {
            final long config = PreferenceManager.getDefaultSharedPreferences(ConfigActivity.this).getLong("current_user", 0);
            final byte dificultad = (byte) level;
            final boolean music = seekBarMusic.getProgress() != 0;
            final boolean sound = seekBarSound.getProgress() != 0;
            final String color =   currentColor != null ? currentColor : "#FFFFFF";

            Configuracion configuracion = new Configuracion();
            configuracion.id_config = config;
            configuracion.dificultad = dificultad;
            configuracion.musica = music;
            configuracion.sonido = sound;
            configuracion.color = color;

            dataBase.getDAO().updateConfig(configuracion);

            //Update Shared
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(ConfigActivity.this).edit();
            editor.putInt("music_volume", seekBarMusic.getProgress());
            editor.putInt("sound_volume", seekBarSound.getProgress());
            editor.commit();

            //Show update message
            runOnUiThread(()-> Toast.makeText(this, "¡Configuración guardada!", Toast.LENGTH_LONG).show());
        });
    }

    private void springAnimation(View view, int level)
    {

        SpringAnimation springAnimationX = new SpringAnimation(view, DynamicAnimation.ROTATION_X, 60f);
        springAnimationX.start();

        if(lastView != null)
        {
            SpringAnimation springAnimationLastX = new SpringAnimation(lastView, DynamicAnimation.ROTATION_X, 0f);

            springAnimationLastX.start();
        }

        this.lastView = view;
        this.level = level;
    }

    private void playSound(View view)
    {
        if(mediaPlayerSound != null)
        {
            if (mediaPlayerSound.isPlaying())
                mediaPlayerSound.stop();

            mediaPlayerSound.start();
        }
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