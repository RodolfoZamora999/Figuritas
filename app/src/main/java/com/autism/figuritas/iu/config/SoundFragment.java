package com.autism.figuritas.iu.config;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.autism.figuritas.R;

/**
 * Fragmento de configuración del sonido
 */
public class SoundFragment extends Fragment
{
    private Bundle bundleConfig;

    private MediaPlayer mediaPlayerMusic;
    private MediaPlayer mediaPlayerSound;

    private TextView txtTitle;

    private ViewGroup viewGroup;

    private SeekBar seekBarMusic;
    private SeekBar seekBarSound;

    private Button btnNext;
    private ImageButton btnSound;

    public SoundFragment()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Check arguments
        if(getArguments() != null)
            bundleConfig = getArguments();
        else
            bundleConfig = new Bundle();

        //Create MediaPlayer
        mediaPlayerMusic = MediaPlayer.create(getContext(), R.raw.ambient_music);
        mediaPlayerSound = MediaPlayer.create(getContext(), R.raw.cuadrado);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_sound, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        //References
        this.txtTitle = getActivity().findViewById(R.id.txtTitleSound);
        this.viewGroup = getActivity().findViewById(R.id.viewGroupSound);
        this.seekBarMusic = getActivity().findViewById(R.id.seekBarMusicaInitConfig);
        this.seekBarSound = getActivity().findViewById(R.id.seekBarSoundInitConfig);
        this.btnNext = getActivity().findViewById(R.id.btnNextSounds);
        this.btnSound = getActivity().findViewById(R.id.btnSoundNowInitConfig);

        //Events for seek bars
        this.seekBarMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                if(mediaPlayerMusic != null)
                    mediaPlayerMusic.setVolume((i * 0.01f),(i * 0.01f));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        //Events for seek bars
        this.seekBarSound.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                if(mediaPlayerSound != null)
                    mediaPlayerSound.setVolume((i * 0.01f),(i * 0.01f));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });


        //Event click
        btnNext.setOnClickListener(  view1 -> nextFragment(view1) );
        btnSound.setOnClickListener(view1 -> playSound(view1));

        String color = bundleConfig.getString("color", "#FFFFFF");
        changeColors(color);
    }

    @Override
    public void onStart()
    {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        //Init music
        if (mediaPlayerMusic == null)
        {
            mediaPlayerMusic = MediaPlayer.create(getContext(), R.raw.ambient_music);
            mediaPlayerMusic.setVolume((seekBarMusic.getProgress() * 0.01f),(seekBarMusic.getProgress() * 0.01f));
        }

       mediaPlayerMusic.start();
    }

    @Override
    public void onPause()
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
    public void onStop()
    {
        super.onStop();

        if(mediaPlayerMusic != null)
        {
            if(mediaPlayerMusic.isPlaying())
                mediaPlayerMusic.stop();

            mediaPlayerMusic.release();

            mediaPlayerMusic = null;
        }

        if(mediaPlayerSound != null)
        {
            if(mediaPlayerSound.isPlaying())
                mediaPlayerSound.stop();

            mediaPlayerSound.release();

            mediaPlayerSound = null;
        }
    }

    /**
     * Method for change colors IU
     * @param colorHex
     */
    private void changeColors(String colorHex)
    {
        int background_color = 0;
        int title_background = 0;
        int button_background = 0;

        switch (colorHex)
        {
            case "#FFC107":
                background_color = R.color.AMBER_BACKGROUND;
                title_background = R.color.AMBER;
                button_background = R.color.AMBER_ANALOGO;
                break;

            case "#00BCD4":
                background_color = R.color.CYAN_BACKGROUND;
                title_background = R.color.CYAN;
                button_background = R.color.CYAN_ANALOGO;
                break;

            case "#FF4081":
                background_color = R.color.PINK_BACKGROUND;
                title_background = R.color.PINK;
                button_background = R.color.PINK_ANALOGO;
                break;

            case "#FF8000":
                background_color = R.color.ORANGE_BACKGROUND;
                title_background = R.color.ORANGE;
                button_background = R.color.ORANGE_ANALOGO;
                break;

            case "#F44336":
                background_color = R.color.RED_BACKGROUND;
                title_background = R.color.RED;
                button_background = R.color.RED_ANALOGO;
                break;

            case "#ACAF50":
                background_color = R.color.GREEN_BACKGROUND;
                title_background = R.color.GREEN;
                button_background = R.color.GREEN_ANALOGO;
                break;

            case "#03A9F4":
                background_color = R.color.LIGHT_BLUE_BACKGROUND;
                title_background = R.color.LIGHT_BLUE;
                button_background = R.color.LIGHT_BLUE_ANALOGO;
                break;

            case "#FFFFFF":
                background_color = R.color.WHITE_BACKGROUND;
                title_background = R.color.WHITE_COMPLEMENTATION;
                button_background = R.color.WHITE_ANALOGO;
                break;

            default:
                background_color = R.color.design_default_color_background;
                title_background = R.color.design_default_color_background;
                button_background = R.color.design_default_color_background;
                break;
        }

        txtTitle.setBackgroundResource(title_background);
        viewGroup.setBackgroundResource(background_color);
        btnNext.setBackgroundResource(button_background);
    }

    private void playSound(View view)
    {
        if(mediaPlayerSound != null)
        {
            if (mediaPlayerSound.isPlaying())
                mediaPlayerSound.stop();
        }
        else
        {
            mediaPlayerSound = MediaPlayer.create(getContext(), R.raw.cuadrado);
            mediaPlayerSound.setVolume((seekBarSound.getProgress() * 0.01f),(seekBarSound.getProgress() * 0.01f));
        }

        mediaPlayerSound.start();
    }

    /**
     * Method for save config sound
     */
    private void saveData()
    {
        //Save volume level in SharedPreferences
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
        editor.putInt("music_volume", seekBarMusic.getProgress());
        editor.putInt("sound_volume", seekBarSound.getProgress());
        editor.commit();

        //Put Bundle
        boolean statusMusic = seekBarMusic.getProgress() != 0;
        boolean statusSound = seekBarSound.getProgress() != 0;

        bundleConfig.putBoolean("music", statusMusic);
        bundleConfig.putBoolean("sounds", statusSound);
    }

    /**
     * Método para pasar al siguiente fragmento de la actividad. Fragment level
     * @param view
     */
    private void nextFragment(View view)
    {
        saveData();

        Navigation.findNavController(view).navigate(R.id.action_soundFragment_to_levelFragment, bundleConfig);
    }
}