package com.autism.figuritas.iu.config;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
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

    private MediaPlayer mediaPlayer;

    private TextView txtTitle;

    private ImageView imgDisco;

    private ViewGroup viewGroup;

    private Switch switchMusic;
    private Switch switchSounds;

    private Button btnNext;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_sound, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();

        //References
        this.txtTitle = getActivity().findViewById(R.id.txtTitleSound);
        this.viewGroup = getActivity().findViewById(R.id.viewGroupSound);
        this.switchMusic = getActivity().findViewById(R.id.switchMusica);
        this.switchSounds = getActivity().findViewById(R.id.switchSonido);
        this.btnNext = getActivity().findViewById(R.id.btnNextSounds);

        //Events for switch
        this.switchMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {

                if(mediaPlayer != null)
                {
                    if(b)
                        mediaPlayer.start();
                    else
                        mediaPlayer.pause();
                }
            }
        });


        this.switchSounds.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {

            }
        });

        //Event click
        btnNext.setOnClickListener(view -> nextFragment(view));

        String color = bundleConfig.getString("color", "#FFFFFF");
        changeColors(color);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        //Init music
        mediaPlayer = MediaPlayer.create(getContext(), R.raw.ambient_music);
        mediaPlayer.start();
    }

    @Override
    public void onStop()
    {
        super.onStop();

        if(mediaPlayer != null)
        {
            if(mediaPlayer.isPlaying())
                mediaPlayer.stop();

            mediaPlayer.release();

            mediaPlayer = null;
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

    /**
     * Método para pasar al siguiente fragmento de la actividad. Fragment level
     * @param view
     */
    private void nextFragment(View view)
    {
        bundleConfig.putBoolean("music", switchMusic.isChecked());
        bundleConfig.putBoolean("sounds", switchSounds.isChecked());

        Navigation.findNavController(view).navigate(R.id.action_soundFragment_to_levelFragment, bundleConfig);
    }
}