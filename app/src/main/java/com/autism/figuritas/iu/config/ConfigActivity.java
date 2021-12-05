package com.autism.figuritas.iu.config;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.autism.figuritas.MyDatabaseApplication;
import com.autism.figuritas.R;
import com.autism.figuritas.persistence.database.Configuration;
import com.autism.figuritas.persistence.database.DataBase;
import com.autism.figuritas.persistence.preferences.ConstantPreferences;

import org.w3c.dom.Text;

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
    private View lastViewColor;

    private int level;
    private String currentColor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_config);

        Toolbar toolbar = findViewById(R.id.toolbarConfig);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

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
        this.btnRegresar.setOnClickListener(view -> initExitDialog(view));


        this.btnSave.setOnClickListener(view ->  saveConfigDatabase(view));
        this.btnLevel1.setOnClickListener(view -> springAnimation(view, 0));
        this.btnLevel2.setOnClickListener(view -> springAnimation(view, 1));
        this.btnLevel3.setOnClickListener(view -> springAnimation(view, 2));
        this.btnSound.setOnClickListener(view -> playSound(view));
        this.imgColor.setOnClickListener(view -> initColorDialog(view));


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
        this.mediaPlayerSound = MediaPlayer.create(this, R.raw.square);


        applyColor();

        //Load database config
        loadConfigDatabase();
    }

    /**
     * Method to update IU config
     * @param configuration
     */
    private void updateConfigIU(Configuration configuration)
    {
        this.mediaPlayerMusic.setVolume((configuration.volumeMusic * 0.01f), (configuration.volumeMusic * 0.01f));
        this.mediaPlayerSound.setVolume((configuration.volumeSound * 0.01f), (configuration.volumeSound * 0.01f));
        this.seekBarMusic.setProgress(configuration.volumeMusic);
        this.seekBarSound.setProgress(configuration.volumeSound);

        switch (configuration.color)
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

        switch (configuration.dificultad)
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
            final long idConfig = preferences.getLong(ConstantPreferences.CURRENT_USER, 0);
            int volumeMusic = preferences.getInt(ConstantPreferences.MUSIC_VOLUME, 40);
            int volumeSound = preferences.getInt(ConstantPreferences.SOUND_VOLUME, 70);

            final Configuration configuration = database.getDAO().getConfig(idConfig);
            configuration.volumeMusic = volumeMusic;
            configuration.volumeSound = volumeSound;

            runOnUiThread(()->
                    updateConfigIU(configuration));
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
            final byte difficulty = (byte) level;
            final boolean music = seekBarMusic.getProgress() != 0;
            final boolean sound = seekBarSound.getProgress() != 0;
            final String color =   currentColor != null ? currentColor : "#FFFFFF";

            Configuration configuration = new Configuration();
            configuration.id_config = config;
            configuration.dificultad = difficulty;
            configuration.musica = music;
            configuration.sonido = sound;
            configuration.color = color;

            dataBase.getDAO().updateConfig(configuration);

            //Update Shared
            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(ConfigActivity.this).edit();
            editor.putInt(ConstantPreferences.MUSIC_VOLUME, seekBarMusic.getProgress());
            editor.putInt(ConstantPreferences.SOUND_VOLUME, seekBarSound.getProgress());
            editor.putString(ConstantPreferences.CURRENT_COLOR, color.replace("#", "#FF")).apply();

            //Show update message
            runOnUiThread(()-> {
                Toast toast = Toast.makeText(this, R.string.configuracion_guardada, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 120, 100);
                toast.show();
            }
            );
        });
    }

    /**
     * Method to animate level buttons
     * @param view
     * @param level
     */
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

    /**
     * Method to play Test volume sound
     * @param view
     */
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
     * Method to animate Dialog Buttons colors
     * @param view
     */
    private void springAnimationDialog(View view)
    {
        SpringAnimation springAnimationX = new SpringAnimation(view, DynamicAnimation.SCALE_X, 0.6f);
        SpringAnimation springAnimationY = new SpringAnimation(view, DynamicAnimation.SCALE_Y, 0.6f);
        springAnimationX.start();
        springAnimationY.start();

        if(lastViewColor != null)
        {
            SpringAnimation springAnimationXLast = new SpringAnimation(lastViewColor, DynamicAnimation.SCALE_X, 1.0f);
            SpringAnimation springAnimationYLast = new SpringAnimation(lastViewColor, DynamicAnimation.SCALE_Y, 1.0f);
            springAnimationXLast.start();
            springAnimationYLast.start();
        }

        //Get and set Drawable to ImgColor
        imgColor.setImageDrawable(view.getBackground());

        //Update color String current
        if(view.getTag() != null)
            currentColor = view.getTag().toString();

        lastViewColor = view;
    }

    /**
     * Method to show a AlertDialog with Colors
     * @param view
     */
    private void initColorDialog(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //Inflate View
        ViewGroup viewGroup = (ViewGroup) getLayoutInflater().inflate(R.layout.dialog_colors, null);

        builder.setView(viewGroup);

        //Magic :D
        for(int i = 0; i < viewGroup.getChildCount(); i++)
            viewGroup.getChildAt(i).setOnClickListener(view1 -> springAnimationDialog(view1));

        builder.setPositiveButton(R.string.aceptar, (dialogInterface, i) ->
        {
            dialogInterface.dismiss();
            applyColor();
        });

        //More magic
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);

        // Set alertDialog "not focusable" so nav bar still hiding:
        alertDialog.getWindow().
                setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        // Set full-screen mode (immersive sticky):
        alertDialog.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        alertDialog.show();

        // Set dialog focusable so we can avoid touching outside:
        alertDialog.getWindow().
                clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }

    /**
     * Method to show an AlertDialog
     * @param view
     */
    private void initExitDialog(View view)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.confirmacion_salir);
        builder.setMessage(R.string.message_exit_config);
        builder.setPositiveButton(R.string.aceptar, (dialogInterface, i) ->
        {
            //Confirmation to exit event
            super.onBackPressed();
        });
        builder.setNegativeButton(R.string.cancelar, ((dialogInterface, i) -> {
            //Cancel exit event
            dialogInterface.dismiss();
        }));

        //More magi
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);

        // Set alertDialog "not focusable" so nav bar still hiding:
        alertDialog.getWindow().
                setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        // Set full-screen mode (immersive sticky):
        alertDialog.getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        alertDialog.show();

        // Set dialog focusable so we can avoid touching outside:
        alertDialog.getWindow().
                clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }

    /**
     * Override method to call DialogAlert
     */
    @Override
    public void onBackPressed()
    {
       // super.onBackPressed();
        initExitDialog(null);
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

    private void applyColor() {
       // SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String colorHex =   currentColor != null ? currentColor.toUpperCase().replace("#", "#FF") : PreferenceManager.
                getDefaultSharedPreferences(this).getString(ConstantPreferences.CURRENT_COLOR, "#FFFFFFFF");

        int background_color = 0;
        int title_background = 0;
        int button_background = 0;

        switch (colorHex) {
            case "#FFFFC107":
                Log.d("color", "Amber section now!");
                background_color = R.color.AMBER_BACKGROUND;
                title_background = R.color.AMBER;
                button_background = R.color.AMBER_ANALOGO;
                break;

            case "#FF00BCD4":
                background_color = R.color.CYAN_BACKGROUND;
                title_background = R.color.CYAN;
                button_background = R.color.CYAN_ANALOGO;
                break;

            case "#FFFF4081":
                background_color = R.color.PINK_BACKGROUND;
                title_background = R.color.PINK;
                button_background = R.color.PINK_ANALOGO;
                break;

            case "#FFFF8000":
                background_color = R.color.ORANGE_BACKGROUND;
                title_background = R.color.ORANGE;
                button_background = R.color.ORANGE_ANALOGO;
                break;

            case "#FFF44336":
                background_color = R.color.RED_BACKGROUND;
                title_background = R.color.RED;
                button_background = R.color.RED_ANALOGO;
                break;

            case "#FFACAF50":
                background_color = R.color.GREEN_BACKGROUND;
                title_background = R.color.GREEN;
                button_background = R.color.GREEN_ANALOGO;
                break;

            case "#FF03A9F4":
                background_color = R.color.LIGHT_BLUE_BACKGROUND;
                title_background = R.color.LIGHT_BLUE;
                button_background = R.color.LIGHT_BLUE_ANALOGO;
                break;

            case "#FFFFFFFF":
                background_color = R.color.WHITE_BACKGROUND;
                title_background = R.color.WHITE_COMPLEMENTATION;
                button_background = R.color.WHITE_ANALOGO;
                break;

            default:
                Log.d("color", "Use default color");
                background_color = R.color.design_default_color_background;
                title_background = R.color.design_default_color_background;
                button_background = R.color.design_default_color_background;
                break;
        }

        //Apply colors to components
        Toolbar toolbar = findViewById(R.id.toolbarConfig);
        toolbar.setBackgroundColor(getColor(title_background));

        SeekBar seekBarMusic = findViewById(R.id.seekBarMusicaConfig);
        SeekBar seekBarSound = findViewById(R.id.seekBarSoundConfig);

        //seekBarMusic.setBackgroundColor(getColor(title_background));
        //seekBarSound.setBackgroundColor(getColor(title_background));
        seekBarMusic.setProgressTintList(ColorStateList.valueOf(getColor(title_background)));
        seekBarSound.setProgressTintList(ColorStateList.valueOf(getColor(title_background)));

        ImageButton btnSound = findViewById(R.id.btnSoundNowConfig);
        Drawable drawable = getDrawable(R.drawable.button_background_circle);
        drawable.setTint(getColor(title_background));
        btnSound.setBackground(drawable);

        Drawable drawableBackground = getDrawable(R.drawable.ic_backgroud_main);
        drawableBackground.setColorFilter(getColor(background_color), PorterDuff.Mode.DST_OVER);

        ViewGroup background = findViewById(R.id.background_config);
        background.setBackground(drawableBackground);

        TextView txtSound = findViewById(R.id.txtSonido);
        TextView txtMusic = findViewById(R.id.txtMusica);
        TextView txtColor = findViewById(R.id.txtColor);
        TextView txtNivel = findViewById(R.id.txtNivel);

        txtSound.setTextColor(getColor(title_background));
        txtMusic.setTextColor(getColor(title_background));
        txtColor.setTextColor(getColor(title_background));
        txtNivel.setTextColor(getColor(title_background));
    }
}